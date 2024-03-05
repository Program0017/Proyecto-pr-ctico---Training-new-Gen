package com.globant.project.foodAplication.utils.client;

import com.globant.project.foodAplication.model.client.Client;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class ClientValidation {

    public static void documentValidation(String documentClient){
        if (documentClient.length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The document is empty");
        }
    }

    public static void clientEmptyValidation(Optional<Client> client, String documentClient){
        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The client with document %s doesn't exist", documentClient));
        }
    }

    public static void clientPresentValidation(Optional<Client> client, String documentClient){
        if (client.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The client with document %s already exist", documentClient));
        }
    }

    public static void clientTotalValidation(Client client){
        if(client.getDocument().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, client.getDocument());
        if(client.getEmail().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, client.getEmail());
        if(client.getPhone().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, client.getPhone());
        if(client.getDeliveryAddress().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, client.getDeliveryAddress());
    }

    public static void clientEqualValidation(Client oldClient, Client newClient){
        if (oldClient.getDocument().equals(newClient.getDocument()) &&
                oldClient.getName().equals(newClient.getName()) &&
                oldClient.getEmail().equals(newClient.getEmail()) &&
                oldClient.getDeliveryAddress().equals(newClient.getDeliveryAddress()) &&
                oldClient.getPhone().equals(newClient.getPhone())
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The request failed because the client is equal, it doesn't have different values");
        }
    }
}
