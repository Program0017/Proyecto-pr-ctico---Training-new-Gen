package com.globant.project.foodAplication.webApi.client;

import com.globant.project.foodAplication.commons.constants.endPoints.client.IClientEndPoints;
import com.globant.project.foodAplication.model.client.Client;
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
    public String createClient(@RequestBody Client client){
        return this.clientService.createClient(client);
    }

    @GetMapping(IClientEndPoints.CLIENT_GET_URL)
    public ResponseEntity<Client> getUser(@PathVariable("document") String document){
        return new ResponseEntity<Client>(clientService.findByDocument(document), HttpStatus.OK);
    }

    @PutMapping(IClientEndPoints.CLIENT_UPDATE_URL)
    public ResponseEntity<Client> update(@PathVariable("document") String document, @RequestBody Client client){
        Client updatedClient = clientService.updateClient(document, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @PatchMapping(IClientEndPoints.CLIENT_DESACTIVATE_URL)
    public ResponseEntity<Client> desactivate(@PathVariable("document") String document){
        Client desactivatedclient = clientService.desactivateClient(document);
        return new ResponseEntity<>(desactivatedclient, HttpStatus.OK);
    }
}
