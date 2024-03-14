package com.globant.project.foodAplication.testClient;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.mapper.ClientMapper;
import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.service.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientEntityTest {

    @Mock
    private IClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;
    @Test
    public void testCreateClientSuccess(){
        /*
        ClientDto clientDto = ClientDto.builder()
                .document("1004798908")
                .name("fulanito")
                .email("pepito32@gmail.com")
                .phone("3333335")
                .deliveryAddress("Avenida Siempre vivo")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1);
        clientEntity.setDocument("1004798908");
        clientEntity.setName("fulanito");
        clientEntity.setEmail("pepito32@gmail.com");
        clientEntity.setPhone("3333335");
        clientEntity.setDeliveryAddress("Avenida Siempre vivo");


        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        ClientDto result = clientService.createClient(clientMapper.mapEntityToDto(clientEntity) );
        assertEquals(IResponse.CREATE_SUCCESS, result);

        verify(clientRepository, times(1)).save(clientEntity);
        verify(clientRepository, times(1)).findById(1);

        verifyNoMoreInteractions(clientRepository);

         */
    }

    @Test
    public void testCreateClientFail(){
//        ClientDto clientDto = ClientDto.builder()
//                .id(1)
//                .document("1004798908")
//                .name("fulanito")
//                .email("pepito32@gmail.com")
//                .phone("3333335")
//                .deliveryAddress("Avenida Siempre vivo")
//                .build();
//        ClientEntity clientEntity = new ClientEntity();
//        clientEntity.setId(1);
//        clientEntity.setDocument("1004798908");
//        clientEntity.setName("fulanito");
//        clientEntity.setEmail("pepito32@gmail.com");
//        clientEntity.setPhone("3333335");
//        clientEntity.setDeliveryAddress("Avenida Siempre vivo");
//
//
//        when(clientRepository.findById(1)).thenReturn(Optional.of(clientEntity));
//
//        String result = clientService.createClient(clientEntity);
//        assertEquals(IResponse.CREATE_FAIL, result);
//
//        verify(clientRepository, times(1)).findById(1);
//
//        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void testUpdateClientWhenClientExists() {
//        String document = "1004798908";
//        ClientEntity existingClientEntity = new ClientEntity();
//        ClientEntity updatedClientEntity = new ClientEntity();
//
//        when(clientRepository.findByDocument(document)).thenReturn(Optional.of(existingClientEntity));
//        when(clientRepository.save(updatedClientEntity)).thenReturn(updatedClientEntity);
//
//        ClientEntity result = clientService.updateClient(document, updatedClientEntity);
//
//        assertEquals(updatedClientEntity, result);
//        verify(clientRepository, times(1)).findByDocument(document);
//        verify(clientRepository, times(1)).save(updatedClientEntity);
    }


    @Test
    public void testDesactivateClientWhenClientExists() {

//        String document = "123456789";
//        ClientEntity existingClientEntity = new ClientEntity();
//        existingClientEntity.setIsActive(true);
//
//        when(clientRepository.findByDocument(document)).thenReturn(Optional.of(existingClientEntity));
//        when(clientRepository.save(existingClientEntity)).thenReturn(existingClientEntity);
//
//        ClientEntity result = clientService.desactivateClient(document);
//
//        assertFalse(result.getIsActive());
//        verify(clientRepository, times(1)).findByDocument(document);
//        verify(clientRepository, times(1)).save(existingClientEntity);
    }
}
