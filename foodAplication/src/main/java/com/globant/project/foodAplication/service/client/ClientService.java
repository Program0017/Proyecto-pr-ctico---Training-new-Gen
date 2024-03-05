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

    public ClientEntity findByDocument(String document){
        return clientRepository.findByDocument(document).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d with this document does not exist", document)));
    }

    public ClientDto createClient(ClientDto clientDto) {
            Optional<ClientEntity> find = this.clientRepository.findByDocument(clientDto.getDocument());
            if (!find.isPresent()){
                ClientEntity clientEntity = clientMapper.mapDtoToEntity(clientDto);
                return clientMapper.mapEntityDtoTo(clientRepository.save(clientEntity));
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with document %s does not exist", clientDto.getDocument()));
            }
    }

    public ClientEntity updateClient(String document, ClientEntity clientEntity) {
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
            return this.clientRepository.save(clientEntity);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
        }
    }

    public ClientEntity desactivateClient(String document) {
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        if (result.isPresent()){
        result.get().setIsActive(!result.get().getIsActive());
        return this.clientRepository.save(result.get());
    }else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("User %d with this document does not exist", document));
    }}
}
