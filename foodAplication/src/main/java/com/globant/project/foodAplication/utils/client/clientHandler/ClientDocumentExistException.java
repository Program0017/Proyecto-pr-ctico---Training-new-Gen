package com.globant.project.foodAplication.utils.client.clientHandler;

public class ClientDocumentExistException extends RuntimeException{
    public ClientDocumentExistException(String message){
        super(message);
    }
}
