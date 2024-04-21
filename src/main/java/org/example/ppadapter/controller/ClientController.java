package org.example.ppadapter.controller;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("getClients")
    public List<Clients> getAllClients() {
        return clientService.getAll();
    }

    @PostMapping("getClients/{clientId}")
    public Clients fetchClientById(@PathVariable Long clientId) {
        return clientService.clientById(clientId);
    }
}

