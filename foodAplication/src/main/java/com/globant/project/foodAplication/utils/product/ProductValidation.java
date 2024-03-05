package com.globant.project.foodAplication.utils.product;

import com.globant.project.foodAplication.model.product.ProductEntity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

public class ProductValidation {

    public static void productPresentValidation(Optional<ProductEntity> existProduct, String fantasyName){
        if (existProduct.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("There is a product with the same fantasy name: %s", fantasyName));
    }

    public static void productTotalValidation(ProductEntity productEntity){
        if (productEntity.getFantasyName() == null || productEntity.getFantasyName().isEmpty() ||
                productEntity.getDescription() == null || productEntity.getDescription().isEmpty() ||
                productEntity.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more values are empty or invalid");
        }
    }

    public static void productEmptyValidation(Optional<ProductEntity> product){
        if(product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user doesn't exist");
    }

    public static void productFormatUuid(UUID uuidProduct){
        try {
            UUID.fromString(uuidProduct.toString());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid UUID format");
        }
    }

    public static void productEqualValidation(Optional<ProductEntity> existingProduct, ProductEntity productEntity){
        if (existingProduct.get().getFantasyName().equals(productEntity.getFantasyName()) &&
                existingProduct.get().getDescription().equals(productEntity.getDescription()) &&
                existingProduct.get().getCategory().equals(productEntity.getCategory()) &&
                existingProduct.get().getPrice().equals(productEntity.getPrice()) &&
                existingProduct.get().getAvailable() == productEntity.getAvailable()
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The request failed because the product is equal, it doesn't have different values");
        }
    }

    public static void productFantasyNameValidation(Optional<ProductEntity> otherProduct){
        if(otherProduct.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "There is a product with the same name in the data base");
    }

    public static void productFantasyNameUUIDValidation(Optional<ProductEntity> otherProduct, UUID uuid){
        if(otherProduct.isPresent() && !otherProduct.get().getUuid().equals(uuid)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "There is a product with the same name in the database");
        }
    }
}
