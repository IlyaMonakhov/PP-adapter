package org.example.ppadapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.example.ppadapter.controller.ClientController;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Clients client1;


    @BeforeEach
    void setUp() {
        client1 = new Clients(2L, "Петр Петрович Петров", "+79996155507", new Date(1989, 4, 10),true);
    }

    @Test
    void getAllClients_shouldReturnAllClients() {

        List<Clients> clients = Arrays.asList(client1);
        when(clientService.getAll()).thenReturn(clients);


        List<Clients> result = clientController.getAllClients();


        assertThat(result).hasSize(1);
        assertThat(result).containsExactlyInAnyOrder(client1);
    }

    @Test
    void fetchClientById_shouldReturnClientById() {

        when(clientService.clientById(2L)).thenReturn(client1);


        Clients result = clientController.getClientById(2L);


        assertThat(result).isEqualTo(client1);
    }
}