package com.globant.project.foodAplication.service;

import com.globant.project.foodAplication.model.Product;
import com.globant.project.foodAplication.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public Product findById(Long productId){
        return this.productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product %d does not exist", productId)));
    }

    public Product createProduct(Product product){ return this.productRepository.save(product); }

    public Product updateProduct(Long productId, Product product){
        Optional<Product> result = this.productRepository.findById(productId);
        if (result.isPresent()){
            Product existingProduct = result.get();

            existingProduct.setFantasyName(product.getFantasyName());
            existingProduct.setDescription(product.getDescription());

            return this.productRepository.save(existingProduct);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Producto with id %d does not exist", productId));
        }
    }

    public Product deleteProduct(Long productId){
        Optional<Product> result = this.productRepository.findById(productId);
        if(result.isPresent()){
            this.productRepository.delete(result.get());
            return result.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id %d does not exist", productId));
        }
    }

}
