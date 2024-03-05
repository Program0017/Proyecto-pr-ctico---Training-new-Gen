package com.globant.project.foodAplication.webApi.client;

import com.globant.project.foodAplication.commons.constants.endPoints.client.IClientEndPoints;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IClientEndPoints.CLIENT_BASE_URL)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(IClientEndPoints.CLIENT_CREATE_URL)
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        ClientDto newClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
//Probar con dto los controlador -> proxima tarea
    @GetMapping(IClientEndPoints.CLIENT_GET_URL)
    public ResponseEntity<ClientEntity> getUser(@PathVariable("document") String document){
        return new ResponseEntity<>(clientService.findByDocument(document), HttpStatus.OK);
    }

    @PutMapping(IClientEndPoints.CLIENT_UPDATE_URL)
    public ResponseEntity<ClientEntity> update(@PathVariable("document") String document, @RequestBody ClientEntity clientEntity){
        ClientEntity updatedClientEntity = clientService.updateClient(document, clientEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(IClientEndPoints.CLIENT_DESACTIVATE_URL)
    public ResponseEntity<ClientEntity> desactivate(@PathVariable("document") String document){
        ClientEntity desactivatedclient = clientService.desactivateClient(document);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
