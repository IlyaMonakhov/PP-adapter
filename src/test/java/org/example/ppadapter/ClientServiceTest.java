package org.example.ppadapter;

import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.mapper.DTOMap;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.modelClients.Message;
import org.example.ppadapter.repository.ClientRepository;
import org.example.ppadapter.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientsFeignClient clientsFeignClient;

    @Mock
    private DTOMap dtoMapper;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private KafkaTemplate<String, Message> kafkaTemplate;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {

        List<ClientINFO> clientInfoList = List.of(
                new ClientINFO(2L, "Петр", "Петрович", "Петров", 35, new Date(1989, 4, 10), "+79996155507")
        );
        when(clientsFeignClient.allGetClients()).thenReturn(clientInfoList);

        Clients mappedClient1 = new Clients(2L, "Петр Петрович Петров", "+79996155507", new Date(1989, 4, 10),true);
        when(dtoMapper.map(any(ClientINFO.class))).thenReturn(mappedClient1);


        List<Clients> result = clientService.getAll();


        assertEquals(1, result.size());
        verify(kafkaTemplate, times(1)).send(anyString(), any(Message.class));
        verify(clientRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testClientById() {

        Long clientId = 1L;
        ClientINFO clientInfo = new ClientINFO(2L, "Петр", "Петрович", "Петров", 35, new Date(1989, 4, 10), "+79996155507");
        when(clientsFeignClient.clientById(clientId)).thenReturn(clientInfo);

        Clients mappedClient = new Clients(2L, "Петр Петрович Петров", "+79996155507",new Date(1989, 4, 10),true);
        when(dtoMapper.map(clientInfo)).thenReturn(mappedClient);


        Clients result = clientService.clientById(clientId);


        assertEquals(mappedClient, result);
        verify(kafkaTemplate, times(1)).send(anyString(), any(Message.class));
        verify(clientRepository, times(1)).save(mappedClient);
    }
}