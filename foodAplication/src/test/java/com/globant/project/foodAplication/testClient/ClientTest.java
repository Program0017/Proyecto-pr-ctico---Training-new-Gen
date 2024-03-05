package com.globant.project.foodAplication.testClient;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.service.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private IClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;
    @Test
    public void testCreateClientSuccess(){
        ClientDto clientDto = ClientDto.builder()
                .id(1)
                .document("1004798908")
                .name("fulanito")
                .email("pepito32@gmail.com")
                .phone("3333335")
                .deliveryAddress("Avenida Siempre vivo")
                .build();
        Client client = new Client();
        client.setId(1);
        client.setDocument("1004798908");
        client.setName("fulanito");
        client.setEmail("pepito32@gmail.com");
        client.setPhone("3333335");
        client.setDeliveryAddress("Avenida Siempre vivo");


        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        String result = clientService.createClient(client);
        assertEquals(IResponse.CREATE_SUCCESS, result);

        verify(clientRepository, times(1)).save(client);
        verify(clientRepository, times(1)).findById(1);

        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void testCreateClientFail(){
        ClientDto clientDto = ClientDto.builder()
                .id(1)
                .document("1004798908")
                .name("fulanito")
                .email("pepito32@gmail.com")
                .phone("3333335")
                .deliveryAddress("Avenida Siempre vivo")
                .build();
        Client client = new Client();
        client.setId(1);
        client.setDocument("1004798908");
        client.setName("fulanito");
        client.setEmail("pepito32@gmail.com");
        client.setPhone("3333335");
        client.setDeliveryAddress("Avenida Siempre vivo");


        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        String result = clientService.createClient(client);
        assertEquals(IResponse.CREATE_FAIL, result);

        verify(clientRepository, times(1)).findById(1);

        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void testUpdateClientWhenClientExists() {
        String document = "1004798908";
        Client existingClient = new Client();
        Client updatedClient = new Client();

        when(clientRepository.findByDocument(document)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        Client result = clientService.updateClient(document, updatedClient);

        assertEquals(updatedClient, result);
        verify(clientRepository, times(1)).findByDocument(document);
        verify(clientRepository, times(1)).save(updatedClient);
    }


    @Test
    public void testDesactivateClientWhenClientExists() {

        String document = "123456789";
        Client existingClient = new Client();
        existingClient.setIsActive(true);

        when(clientRepository.findByDocument(document)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client result = clientService.desactivateClient(document);

        assertFalse(result.getIsActive());
        verify(clientRepository, times(1)).findByDocument(document);
        verify(clientRepository, times(1)).save(existingClient);
    }
}
