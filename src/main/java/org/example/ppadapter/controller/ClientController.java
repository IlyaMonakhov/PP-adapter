package org.example.ppadapter.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all clients",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clients.class)) })
    })
    @GetMapping("getClients")
    public List<Clients> getAllClients() {
        return clientService.getAll();
    }

    @Operation(summary = "Get client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Clients.class)) }),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content)
    })
    @PostMapping("getClients/{clientId}")
    public Clients getClientById(@Parameter(description = "ID of the client to be fetched") @PathVariable Long clientId) {
        return clientService.clientById(clientId);
    }
}

