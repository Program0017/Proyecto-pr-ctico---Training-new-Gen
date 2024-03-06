package com.globant.project.foodAplication.utils.product;

import com.globant.project.foodAplication.model.product.ProductEntity;

import com.globant.project.foodAplication.utils.product.productHandler.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@ControllerAdvice
public class ProductValidation {

    public static void productPresentValidation(Optional<ProductEntity> existProduct, String fantasyName){
        if (existProduct.isPresent())
            throw new ProductFantasyNameExistException(String.format("There is a product with the same fantasy name: %s", fantasyName));
    }

    public static void productTotalValidation(ProductEntity productEntity){
        if (productEntity.getFantasyName() == null || productEntity.getFantasyName().isEmpty() ||
                productEntity.getDescription() == null || productEntity.getDescription().isEmpty() ||
                productEntity.getPrice() <= 0) {
            throw new ProductAttributesFormatException("Incorrect format or empty attribute");
        }
    }

    public static void productEmptyValidation(Optional<ProductEntity> product){
        if(product.isEmpty())
            throw new ProductEmptyException("The product doesn't exist");
    }

    public static void productFormatUuid(UUID uuidProduct){
        try {
            UUID.fromString(uuidProduct.toString());
        } catch (IllegalArgumentException e) {
            throw new ProductUuidFormatException("Invalid UUID format");
        }
    }

    public static void productEqualValidation(Optional<ProductEntity> existingProduct, ProductEntity productEntity){
        if (existingProduct.get().getFantasyName().equals(productEntity.getFantasyName()) &&
                existingProduct.get().getDescription().equals(productEntity.getDescription()) &&
                existingProduct.get().getCategory().equals(productEntity.getCategory()) &&
                existingProduct.get().getPrice().equals(productEntity.getPrice()) &&
                existingProduct.get().getAvailable() == productEntity.getAvailable()
        ) {
            throw new ProductEqualException("The request failed because the product is equal, it doesn't have different values");
        }
    }

    public static void productFantasyNameValidation(Optional<ProductEntity> otherProduct){
        if(otherProduct.isPresent())
            throw new ProductFantasyNameExistException("There is a product with the same name in the data base");
    }

    public static void productFantasyNameUUIDValidation(Optional<ProductEntity> otherProduct, UUID uuid){
        if(otherProduct.isPresent() && !otherProduct.get().getUuid().equals(uuid)) {
            throw new ProductFantasyNameExistException("There is a product with the same name in the database");
        }
    }
}
