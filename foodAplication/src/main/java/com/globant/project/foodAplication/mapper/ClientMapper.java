package com.globant.project.foodAplication.mapper;

import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.model.client.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientEntity mapDtoToEntity(ClientDto clientDto){
        return ClientEntity.builder()
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .name(clientDto.getName())
                .document(clientDto.getDocument())
                .build();
    }
    public  ClientDto mapEntityDtoTo(ClientEntity clientEntity){
        ClientDto clientDto = new ClientDto();
        clientDto.setDocument(clientEntity.getDocument());
        clientDto.setEmail(clientEntity.getEmail());
        clientDto.setName(clientEntity.getName());
        clientDto.setDeliveryAddress(clientEntity.getDeliveryAddress());
        clientDto.setPhone(clientEntity.getPhone());
        return clientDto;
    }

}
