package com.globant.project.foodAplication.mapper;

import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.model.client.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientEntity mapDtoToEntity(ClientDto clientDto){
        return ClientEntity.builder()

                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .document(clientDto.getDocument())
                .phone(clientDto.getPhone())
                .deliveryAddress(clientDto.getDeliveryAddress())
                .isActive(clientDto.getIsActive())
                .build();
    }
    public  ClientDto mapEntityToDto(ClientEntity clientEntity){
        ClientDto clientDto = new ClientDto();
        clientDto.setName(clientEntity.getName());
        clientDto.setEmail(clientEntity.getEmail());
        clientDto.setDocument(clientEntity.getDocument());
        clientDto.setPhone(clientEntity.getPhone());
        clientDto.setDeliveryAddress(clientEntity.getDeliveryAddress());
        clientDto.setIsActive(clientEntity.getIsActive());
        return clientDto;
    }

}
