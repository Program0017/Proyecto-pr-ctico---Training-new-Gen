package com.globant.project.foodAplication.service.product;

import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public Product createProduct(Product product){
        product.setUuid(UUID.randomUUID());
        return this.productRepository.save(product); }

    public Product findByUUID(UUID uuid){
        return this.productRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Product updateProduct(UUID uuid, Product product){
        Optional<Product> result = this.productRepository.findByUuid(uuid);
        if (result.isPresent()){
            Product existingProduct = result.get();

            existingProduct.setFantasyName(product.getFantasyName());
            existingProduct.setDescription(product.getDescription());

            return this.productRepository.save(existingProduct);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    //pensar en metodo de resta de stock
    public Product desactivateProduct(UUID uuid){
        Optional<Product> result = this.productRepository.findByUuid(uuid);
        if(result.isPresent()){
            result.get().setAvailable((!result.get().getAvailable()));
            return this.productRepository.save(result.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
