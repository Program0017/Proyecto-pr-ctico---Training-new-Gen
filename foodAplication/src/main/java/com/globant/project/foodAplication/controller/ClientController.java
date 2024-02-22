package com.globant.project.foodAplication.controller;

import com.globant.project.foodAplication.model.Client;
import com.globant.project.foodAplication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create (@RequestBody Client client){
        Client createClient = clientService.create(client);
        return new ResponseEntity<>(createClient, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Client> getUser(@PathVariable("userId") Long userId){
        return new ResponseEntity<Client>(clientService.findById(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Client> update(@PathVariable("userId") Long userId, @RequestBody Client client){
        Client updatedClient = clientService.updateClient(userId, client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

}
