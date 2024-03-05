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
<<<<<<< HEAD
        clientDto.setDocument(clientEntity.getDocument());
        clientDto.setEmail(clientEntity.getEmail());
        clientDto.setName(clientEntity.getName());
        clientDto.setDeliveryAddress(clientEntity.getDeliveryAddress());
        clientDto.setPhone(clientEntity.getPhone());
=======
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setDocument(client.getDocument());
        clientDto.setPhone(client.getPhone());
        clientDto.setDeliveryAddress(client.getDeliveryAddress());
>>>>>>> 4a3bd8605b5855ee70ae4b83f577bef6c726f79a
        return clientDto;
    }

}
