package com.globant.project.foodAplication.service.product;

import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.utils.product.ProductValidation;
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
        Optional<Product> existProduct = productRepository.findByFantasyName(product.getFantasyName().toUpperCase());
        ProductValidation.productPresentValidation(existProduct, product.getFantasyName());
        ProductValidation.productTotalValidation(product);

        String upperCaseFantasyName = product.getFantasyName().toUpperCase();
        product.setFantasyName(upperCaseFantasyName);
        return productRepository.save(product); }

    public Product findByUUID(UUID uuidProduct){
        Optional<Product> product = productRepository.findByUuid(uuidProduct);
        ProductValidation.productEmptyValidation(product);
        ProductValidation.productFormatUuid(uuidProduct);

        return product.get();    }

    public Product updateProduct(UUID uuid, Product product){
        Optional<Product> existingProduct = productRepository.findByUuid(uuid);
        ProductValidation.productEmptyValidation(existingProduct);
        ProductValidation.productEqualValidation(existingProduct, product);

        Optional<Product> otherProduct = productRepository.findByFantasyName(product.getFantasyName());
        ProductValidation.productFantasyNameValidation(otherProduct);

        otherProduct = productRepository.findByFantasyName(product.getFantasyName());
        ProductValidation.productFantasyNameUUIDValidation(otherProduct, uuid);

        ProductValidation.productTotalValidation(product);

        existingProduct.get().setFantasyName(product.getFantasyName());
        existingProduct.get().setCategory(product.getCategory());
        existingProduct.get().setAvailable(product.getAvailable());
        existingProduct.get().setDescription(product.getDescription());
        existingProduct.get().setPrice(product.getPrice());

        return productRepository.save(existingProduct.get());
    }
    //pensar en metodo de resta de stock
    public Product desactivateProduct(UUID uuid){
        Optional<Product> result = this.productRepository.findByUuid(uuid);
        if(result.isPresent()){
            result.get().setAvailable((!result.get().getAvailable()));
            return this.productRepository.save(result.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id %d does not exist", uuid));
        }
    }
}
