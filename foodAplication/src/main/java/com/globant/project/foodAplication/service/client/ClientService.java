package com.globant.project.foodAplication.service.client;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.utils.client.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    public Client findByDocument(String documentClient){
        ClientValidation.documentValidation(documentClient);

        Optional<Client> client = clientRepository.findByDocument(documentClient);

        ClientValidation.clientEmptyValidation(client, documentClient);

        return client.get();    }

    public Client createClient(Client client) {
        ClientValidation.clientTotalValidation(client);

        Optional<Client> existClient = clientRepository.findByDocument(client.getDocument());

        ClientValidation.clientPresentValidation(existClient, client.getDocument());
        return clientRepository.save(client);
    }



    public Client updateClient(String documentClient, Client newClient) {
        Optional<Client> existingClientOptional = clientRepository.findByDocument(documentClient);

        ClientValidation.clientEmptyValidation(existingClientOptional, documentClient);
        ClientValidation.clientEqualValidation(existingClientOptional.get(), newClient);
        ClientValidation.clientTotalValidation(newClient);

        Client existingClient = existingClientOptional.get();

        existingClient.setName(newClient.getName());
        existingClient.setEmail(newClient.getEmail());
        existingClient.setPhone(newClient.getPhone());
        existingClient.setDeliveryAddress(newClient.getDeliveryAddress());

        return clientRepository.save(existingClient);

    }

    public Client desactivateClient(String document) {
        Optional<Client> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
        result.get().setIsActive(!result.get().getIsActive());
        return this.clientRepository.save(result.get());
    }else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
    }}
}
