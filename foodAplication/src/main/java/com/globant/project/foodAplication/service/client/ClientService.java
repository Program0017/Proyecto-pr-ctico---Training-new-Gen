package com.globant.project.foodAplication.service.client;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.mapper.ClientMapper;
import com.globant.project.foodAplication.model.client.ClientEntity;
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
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
            ClientEntity clientEntity = result.get();
            return clientMapper.mapEntityDtoTo(clientEntity);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
        }

    }

    public ClientDto createClient(ClientDto clientDto) {
            Optional<ClientEntity> find = this.clientRepository.findByDocument(clientDto.getDocument());
            if (!find.isPresent()){
                ClientEntity clientEntity = clientMapper.mapDtoToEntity(clientDto);
                return clientMapper.mapEntityDtoTo(clientRepository.save(clientEntity));
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
            }
    }

    public ClientDto updateClient(String document, ClientDto clientDto) {
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
            ClientEntity clientEntity = clientMapper.mapDtoToEntity(clientDto);
            return clientMapper.mapEntityDtoTo(clientRepository.save(clientEntity));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
        }
    }

    public ClientDto desactivateClient(String document) {
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
        ClientEntity clientEntity = result.get();
        clientEntity.setIsActive(!clientEntity.getIsActive());
        ClientEntity updatedClientEntity = this.clientRepository.save(clientEntity);
            return clientMapper.mapEntityDtoTo(updatedClientEntity);
    }else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
    }}
}
