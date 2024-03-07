package com.globant.project.foodAplication.utils.product.productHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductHandlerException {

    @ExceptionHandler(ProductFantasyNameExistException.class)
    public ResponseEntity<String> productFantasyNameExistException(ProductFantasyNameExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT.value())
                .body("Validation error: " + ex.getMessage());
    }

    @ExceptionHandler(ProductAttributesFormatException.class)
    public ResponseEntity<String> productAttributesFormatException(ProductAttributesFormatException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body("Validation error: " + ex.getMessage());
    }

    @ExceptionHandler(ProductEmptyException.class)
    public ResponseEntity<String> productEmptyException(ProductEmptyException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body("Validation error: " + ex.getMessage());
    }

    @ExceptionHandler(ProductUuidFormatException.class)
    public ResponseEntity<String> productUuidFormatException(ProductUuidFormatException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body("Validation error: " + ex.getMessage());
    }

    @ExceptionHandler(ProductEqualException.class)
    public ResponseEntity<String> productEqualException(ProductEqualException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT.value())
                .body("Validation error: " + ex.getMessage());
    }
}
