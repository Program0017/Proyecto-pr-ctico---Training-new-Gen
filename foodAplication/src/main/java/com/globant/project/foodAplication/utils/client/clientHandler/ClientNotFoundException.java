package com.globant.project.foodAplication.utils.client.clientHandler;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message){
        super(message);
    }
}
