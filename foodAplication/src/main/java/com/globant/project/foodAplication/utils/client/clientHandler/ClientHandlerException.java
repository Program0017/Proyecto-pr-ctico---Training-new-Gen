package com.globant.project.foodAplication.utils.client.clientHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientHandlerException {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body("validation error " + ex.getMessage());
    }

    @ExceptionHandler(ClientDocumentFormatException.class)
    public ResponseEntity<String> handleClientDocumentFormatException(ClientDocumentFormatException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body("validation error " + ex.getMessage());
    }

    @ExceptionHandler(ClientDocumentExistException.class)
    public ResponseEntity<String> handleClientDocumentExistException(ClientDocumentExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT.value())
                .body("validation error " + ex.getMessage());
    }

    @ExceptionHandler(ClientAttributeFormatException.class)
    public ResponseEntity<String> handleClientAttributeFormatException(ClientAttributeFormatException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body("Validation error: " + ex.getMessage());
    }

    @ExceptionHandler(ClientEqualException.class)
    public ResponseEntity<String> handleClientEqualException(ClientEqualException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT.value())
                .body("Validation error: " + ex.getMessage());
    }
}
