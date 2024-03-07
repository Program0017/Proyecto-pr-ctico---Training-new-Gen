package com.globant.project.foodAplication.service.client;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.mapper.ClientMapper;
import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.utils.client.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private  ClientMapper clientMapper;

    public ClientDto findByDocument(String document){
        ClientValidation.documentValidation(document);
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        ClientValidation.clientEmptyValidation(result, document);

        ClientEntity clientEntity = result.get();
        return clientMapper.mapEntityToDto(clientEntity);


    }


    public List<ClientEntity> getClients(String orderBy, String direction) {

        if ("NAME".equals(orderBy)) {
            if ("ASC".equals(direction)) {
                return clientRepository.findAllByOrderByNameAsc();
            } else {
                return clientRepository.findAllByOrderByNameDesc();
            }
        }
        else if ("ADDRESS".equals(orderBy)) {
            if ("ASC".equals(direction)) {
                return clientRepository.findAllByOrderByDeliveryAddressAsc();
            } else {
                return clientRepository.findAllByOrderByDeliveryAddressDesc();
            }
        }
        else {
            if ("ASC".equals(direction)) {
                return clientRepository.findAllByOrderByDocumentAsc();
            } else {
                return clientRepository.findAllByOrderByDocumentDesc();
            }
        }
    }

    public ClientDto createClient(ClientDto clientDto) {
        ClientEntity clientEntity = clientMapper.mapDtoToEntity(clientDto);
        ClientValidation.clientTotalValidation(clientEntity);
        Optional<ClientEntity> find = this.clientRepository.findByDocument(clientDto.getDocument());
        ClientValidation.clientPresentValidation(find, clientDto.getDocument());


        return clientMapper.mapEntityToDto(clientRepository.save(clientEntity));

    }

    public ClientDto updateClient(String document, ClientDto clientDto) {
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        ClientValidation.clientEmptyValidation(result, document);
        ClientValidation.clientEqualValidation(result.get(), clientMapper.mapDtoToEntity(clientDto));
        ClientValidation.clientTotalValidation(clientMapper.mapDtoToEntity(clientDto));

        ClientEntity clientEntity = clientMapper.mapDtoToEntity(clientDto);
        ClientEntity client = result.get();

        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setDeliveryAddress(clientDto.getDeliveryAddress());
        clientRepository.save(client);
        return clientMapper.mapEntityToDto(client);

    }

    public ClientDto desactivateClient(String document) {
        ClientValidation.documentValidation(document);
        Optional<ClientEntity> result = this.clientRepository.findByDocument(document);
        ClientValidation.clientEmptyValidation(result, document);

        ClientEntity clientEntity = result.get();
        clientEntity.setIsActive(!clientEntity.getIsActive());
        return clientMapper.mapEntityToDto(clientRepository.save(clientEntity));
    }

}
