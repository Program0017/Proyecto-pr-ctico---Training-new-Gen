package com.globant.project.foodAplication.service.client;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.mapper.ClientMapper;
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

    @Autowired
    private  ClientMapper clientMapper;

    public ClientDto findByDocument(String document){
        Optional<Client> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
            Client client = result.get();
            return clientMapper.mapEntityDtoTo(client);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
        }

    }

    public ClientDto createClient(ClientDto clientDto) {
            Optional<Client> find = this.clientRepository.findByDocument(clientDto.getDocument());
            if (!find.isPresent()){
                Client client = clientMapper.mapDtoToEntity(clientDto);
                return clientMapper.mapEntityDtoTo(clientRepository.save(client));
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
            }
    }

    public ClientDto updateClient(String document, ClientDto clientDto) {
        Optional<Client> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
            Client client = clientMapper.mapDtoToEntity(clientDto);
            return clientMapper.mapEntityDtoTo(clientRepository.save(client));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
        }
    }

    public ClientDto desactivateClient(String document) {
        Optional<Client> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
        Client client = result.get();
        client.setIsActive(!client.getIsActive());
        Client updatedClient = this.clientRepository.save(client);
            return clientMapper.mapEntityDtoTo(updatedClient);
    }else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
    }}
}
