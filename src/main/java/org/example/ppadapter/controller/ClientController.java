package org.example.ppadapter.controller;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.service.ClientService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Clients>> getAllClients() {
        List<Clients> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/api/v1/getClients/{clientId}")
    public ResponseEntity<Clients> getClientById(@PathVariable Long clientId) {
        ClientINFO client = clientService.getClientByID(clientId);
        if (client != null) {
        } else {
            return ResponseEntity.notFound().build();
        }
        return null;
    }
}

