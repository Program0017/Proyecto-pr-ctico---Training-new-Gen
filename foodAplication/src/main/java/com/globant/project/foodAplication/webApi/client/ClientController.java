package com.globant.project.foodAplication.webApi.client;

import com.globant.project.foodAplication.commons.constants.endPoints.client.IClientEndPoints;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.service.client.ClientService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@OpenAPIDefinition(
        info = @Info(
                title = "Grandma´s restaurant",
                version = "1.0",
                description = "Application for a restaurant with: clients, products and orders"
        )
)
@RestController
@RequestMapping(IClientEndPoints.CLIENT_BASE_URL)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Create a client", description = "Add a new client to the system")
    @ApiResponse(responseCode = "201", description = "Client created")
    @PostMapping(IClientEndPoints.CLIENT_CREATE_URL)
    public ResponseEntity<ClientDto> createClient(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Client to be added", required = true, content = @Content(schema = @Schema(implementation = ClientEntity.class)))  @RequestBody ClientDto clientDto){
        ClientDto newClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }


    @Operation(summary = "Get a client", description = "Retrieve a client with document")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientEntity.class)))
    @GetMapping(IClientEndPoints.CLIENT_GET_URL)
    public ResponseEntity<ClientDto> getUser(@PathVariable("document") String document){
        return new ResponseEntity<>(clientService.findByDocument(document), HttpStatus.OK);
    }

    @PutMapping(IClientEndPoints.CLIENT_UPDATE_URL)
    public ResponseEntity<ClientDto> update(@PathVariable("document") String document, @RequestBody ClientDto clientDto){
        ClientDto updatedClientEntity = clientService.updateClient(document , clientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(IClientEndPoints.CLIENT_DESACTIVATE_URL)
    public ResponseEntity<ClientDto> desactivate(@PathVariable("document") String document){
        ClientDto desactivatedclient = clientService.desactivateClient(document);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
