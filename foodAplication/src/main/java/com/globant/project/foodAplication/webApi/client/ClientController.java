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
    public ResponseEntity<Client> getUser(@PathVariable("userId") Long userId){
        return new ResponseEntity<Client>(clientService.findById(userId), HttpStatus.OK);
    }

    @PutMapping(IClientEndPoints.CLIENT_UPDATE_URL)
    public ResponseEntity<Client> update(@PathVariable("userId") Long userId, @RequestBody Client client){
        Client updatedClient = clientService.updateClient(userId, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

}
