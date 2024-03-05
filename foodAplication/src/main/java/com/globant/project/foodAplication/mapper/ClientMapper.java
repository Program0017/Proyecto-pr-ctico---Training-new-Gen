package com.globant.project.foodAplication.mapper;

import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.model.client.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client mapDtoToEntity(ClientDto clientDto){
        return Client.builder()
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .document(clientDto.getDocument())
                .phone(clientDto.getPhone())
                .deliveryAddress(clientDto.getDeliveryAddress())
                .build();
    }
    public  ClientDto mapEntityDtoTo(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setDocument(client.getDocument());
        clientDto.setPhone(client.getPhone());
        clientDto.setDeliveryAddress(client.getDeliveryAddress());
        return clientDto;
    }

}
