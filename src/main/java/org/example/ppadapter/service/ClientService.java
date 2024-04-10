package org.example.ppadapter.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.mapper.DTOMap;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.repository.ClientRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClientService {
    private ClientsFeignClient clientsFeignClient;
    private ClientRepository clientRepository;
    private DTOMap dtoMap;
    private Clients clients;
    private KafkaTemplate<Object, Object> kafkaTemplate;




    public List<Clients> getAll() {
        List<ClientINFO> allClients = clientsFeignClient.allGetClients();
        LocalDate currentDate = LocalDate.now();
        List<Clients> mappedClients = allClients.stream()
                .map(client -> dtoMap.mapClientInfoToClients(client))
                .filter(cl -> cl.getPhone().endsWith("7"))
                .filter(cl -> cl.getBirthday().getMonth() == currentDate.getMonthValue())
                .collect(Collectors.toList());

        clientRepository.saveAll(mappedClients);
        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));
        int discount = currentDate.getMonthValue() == clients.getBirthday().getMonth() ? 10 : 5;

        if (currentTime.isBefore(LocalTime.of(19, 0))) {
            String message = clients.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
            // Отправляем сообщение в топик Kafka "messageSMS"
            kafkaTemplate.send("messageSMS", "{\"phone\": \"" + clients.getPhone() + "\", \"message\": \"" + message + "\"}");
        }

        clients.setMessageSend(true);
        clientRepository.save(clients);


        return mappedClients;
    }

    public ClientINFO getClientByID(long id) {
        return clientsFeignClient.getByIdClient(id);
    }
}






