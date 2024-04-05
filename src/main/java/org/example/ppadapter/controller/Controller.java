package org.example.ppadapter.controller;
import lombok.AllArgsConstructor;
import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class Controller {

    private final ClientService clientService;

    @GetMapping("/api/v1/getClient")
    public void getAllClients(){
       clientService.getAll();

    }
    @GetMapping("/api/v1/getClient/{clientId}")
    public void getClientById(long id){
        clientService.getClientByID(id);
    }




}