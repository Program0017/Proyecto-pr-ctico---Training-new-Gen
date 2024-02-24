package com.globant.project.foodAplication.service.client;

import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class    ClientService {

    @Autowired
    private IClientRepository clientRepository;

    public Client findById(Long userId){
        return clientRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d does not exist", userId)));
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long userId, Client client) {
        Optional<Client> result = clientRepository.findById(userId);
        if (result.isPresent()){
            return clientRepository.save(client);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("Client %s not found", userId));
        }

    }
}
