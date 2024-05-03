package org.example.ppadapter;

import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.mapper.DTOMap;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.modelClients.Message;
import org.example.ppadapter.repository.ClientRepository;
import org.example.ppadapter.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientsFeignClient clientsFeignClient;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private DTOMap dtoMapper;

    @Mock
    private KafkaTemplate<String, Message> kafkaTemplate;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testGetAll() {
        // Given
        ClientINFO client1 = new ClientINFO(2L, "Петр", "Петрович", "Петров", 35, new Date(1989, 5, 10), "+79996155507");
        ClientINFO client2 = new ClientINFO(2L, "Иван", "Иванович", "Иванов", 40, new Date(1990, 7, 20), "+79996155555");
        List<ClientINFO> allClients = Arrays.asList(client1, client2);

        when(clientsFeignClient.allGetClients()).thenReturn(allClients);
        when(dtoMapper.map(client1)).thenReturn(new Clients());
        when(dtoMapper.map(client2)).thenReturn(new Clients());

        // When
        List<Clients> result = clientService.getAll();

        // Then
        assertEquals(1, result.size()); // Ожидаем, что будет возвращен только один клиент с номером телефона, оканчивающимся на "7" и день рождения в этом месяце
        verify(kafkaTemplate, times(1)).send("SMSMassage", any(Message.class)); // Проверяем, что сообщение было отправлено в Kafka
        verify(clientRepository, times(1)).saveAll(anyList()); // Проверяем, что данные были сохранены в базе данных
    }

    @Test
    void testClientById() {
        // Given
        ClientINFO client2 = new ClientINFO(2L, "Петр", "Петрович", "Петров", 35, new Date(1989, 5, 10), "+79996155507");
        when(clientsFeignClient.clientById(2L)).thenReturn(client2);
        when(dtoMapper.map(client2)).thenReturn(new Clients());

        // When
        Clients result = clientService.clientById(2L);

        // Then
        assertEquals("Петр Петрович Петров", result.getFullName()); // Проверяем, что имя клиента верно
        verify(kafkaTemplate, times(1)).send("SMSMassage", any(Message.class)); // Проверяем, что сообщение было отправлено в Kafka
        verify(clientRepository, times(1)).save(any(Clients.class)); // Проверяем, что данные были сохранены в базе данных
    }
}