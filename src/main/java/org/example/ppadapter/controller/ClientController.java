package org.example.ppadapter.controller;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/api/v1/getClients")
    public List<Clients> getAllClients() {
        return clientService.getAll();
    }

    @GetMapping("/api/v1/getClients/{phone}")
    public ClientINFO getClientById(@PathVariable String phone) {
        return clientService.getClientByID(phone);
    }
}

