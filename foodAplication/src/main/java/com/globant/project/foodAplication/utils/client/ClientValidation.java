package com.globant.project.foodAplication.utils.client;

import com.globant.project.foodAplication.model.client.ClientEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class ClientValidation {

    public static void documentValidation(String documentClient){
        if (documentClient.length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The document is empty");
        }
    }

    public static void clientEmptyValidation(Optional<ClientEntity> client, String documentClient){
        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The client with document %s doesn't exist", documentClient));
        }
    }

    public static void clientPresentValidation(Optional<ClientEntity> client, String documentClient){
        if (client.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The client with document %s already exist", documentClient));
        }
    }

    public static void clientTotalValidation(ClientEntity clientEntity){
        if(clientEntity.getDocument().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, clientEntity.getDocument());
        if(clientEntity.getEmail().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, clientEntity.getEmail());
        if(clientEntity.getPhone().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, clientEntity.getPhone());
        if(clientEntity.getDeliveryAddress().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, clientEntity.getDeliveryAddress());
    }

    public static void clientEqualValidation(ClientEntity oldClientEntity, ClientEntity newClientEntity){
        if (oldClientEntity.getDocument().equals(newClientEntity.getDocument()) &&
                oldClientEntity.getName().equals(newClientEntity.getName()) &&
                oldClientEntity.getEmail().equals(newClientEntity.getEmail()) &&
                oldClientEntity.getDeliveryAddress().equals(newClientEntity.getDeliveryAddress()) &&
                oldClientEntity.getPhone().equals(newClientEntity.getPhone())
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The request failed because the client is equal, it doesn't have different values");
        }
    }
}
