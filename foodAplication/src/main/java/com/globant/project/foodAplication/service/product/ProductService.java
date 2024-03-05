package com.globant.project.foodAplication.service.product;

import com.globant.project.foodAplication.model.product.ProductEntity;
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

    public ProductEntity createProduct(ProductEntity productEntity){
        Optional<ProductEntity> existProduct = productRepository.findByFantasyName(productEntity.getFantasyName().toUpperCase());
        ProductValidation.productPresentValidation(existProduct, productEntity.getFantasyName());
        ProductValidation.productTotalValidation(productEntity);

        String upperCaseFantasyName = productEntity.getFantasyName().toUpperCase();
        productEntity.setFantasyName(upperCaseFantasyName);
        return productRepository.save(productEntity); }

    public ProductEntity findByUUID(UUID uuidProduct){
        Optional<ProductEntity> product = productRepository.findByUuid(uuidProduct);
        ProductValidation.productEmptyValidation(product);
        ProductValidation.productFormatUuid(uuidProduct);

        return product.get();    }

    public ProductEntity updateProduct(UUID uuid, ProductEntity productEntity){
        Optional<ProductEntity> existingProduct = productRepository.findByUuid(uuid);
        ProductValidation.productEmptyValidation(existingProduct);
        ProductValidation.productEqualValidation(existingProduct, productEntity);

        Optional<ProductEntity> otherProduct = productRepository.findByFantasyName(productEntity.getFantasyName());
        ProductValidation.productFantasyNameValidation(otherProduct);

        otherProduct = productRepository.findByFantasyName(productEntity.getFantasyName());
        ProductValidation.productFantasyNameUUIDValidation(otherProduct, uuid);

        ProductValidation.productTotalValidation(productEntity);

        existingProduct.get().setFantasyName(productEntity.getFantasyName());
        existingProduct.get().setCategory(productEntity.getCategory());
        existingProduct.get().setAvailable(productEntity.getAvailable());
        existingProduct.get().setDescription(productEntity.getDescription());
        existingProduct.get().setPrice(productEntity.getPrice());

        return productRepository.save(existingProduct.get());
    }
    //pensar en metodo de resta de stock
    public ProductEntity desactivateProduct(UUID uuid){
        Optional<ProductEntity> result = this.productRepository.findByUuid(uuid);
        if(result.isPresent()){
            result.get().setAvailable((!result.get().getAvailable()));
            return this.productRepository.save(result.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id %d does not exist", uuid));
        }
    }
}
