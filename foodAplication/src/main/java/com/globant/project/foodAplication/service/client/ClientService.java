package com.globant.project.foodAplication.service.client;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    public Client findById(Long userId){
        return clientRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d does not exist", userId)));
    }

    public String createClient(Client client){
        try {
            Optional<Client> find = this.clientRepository.findById(client.getId());
            if (!find.isPresent()){
                this.clientRepository.save(client);
                return IResponse.CREATE_SUCCESS;
            }else {
                return IResponse.CREATE_FAIL;
            }
        }catch (Exception e){
            return IResponse.INTERNAL_SERVER_ERROR + e;
        }
    }



    public Client updateClient(Long userId, Client client) {
        Optional<Client> result = this.clientRepository.findById(userId);
        if (result.isPresent()){
            return this.clientRepository.save(client);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("Client %s not found", userId));
        }

    }
}
