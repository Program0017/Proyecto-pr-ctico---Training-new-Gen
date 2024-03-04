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

    public Client findByDocument(String document){
        return clientRepository.findByDocument(document).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String createClient(Client client) {
        try {
            Optional<Client> find = this.clientRepository.findById(client.getId());
            if (!find.isPresent()){
                this.clientRepository.save(client);
                return IResponse.CREATE_SUCCESS;
            }else {
                return IResponse.CREATE_FAIL;
            }
        } catch (Exception e) {
            return IResponse.INTERNAL_SERVER_ERROR + e;
        }
    }



    public Client updateClient(String document, Client client) {
        Optional<Client> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
            return this.clientRepository.save(client);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public Client desactivateClient(String document) {
        Optional<Client> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
        result.get().setIsActive(!result.get().getIsActive());
        return this.clientRepository.save(result.get());
    }else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }}
    //9d5b594f0bcfee5638dd0db7f002f1da6a918289
}
