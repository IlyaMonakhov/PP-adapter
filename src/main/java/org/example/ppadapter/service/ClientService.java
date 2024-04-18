package org.example.ppadapter.service;

import lombok.AllArgsConstructor;
import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.mapper.DTOMap;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.modelClients.Message;
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
public class ClientService {
    private final ClientsFeignClient clientsFeignClient;
    private final ClientRepository clientRepository;
    private final DTOMap dtoMap;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public List<Clients> getAll() {
        List<ClientINFO> allClients = clientsFeignClient.allGetClients();
        System.out.println("Received clients: " + allClients.size());

        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Moscow"));
        List<Clients> mappedClients = allClients.stream()
                .map(dtoMap::map)
                .filter(cl -> {
                    boolean passesFilter = cl.getPhone().endsWith("7") && cl.getBirthday().getMonth() == (currentDate.getMonthValue());
                    if (!passesFilter) {
                        System.out.println("Client does not pass filter: " + cl);
                    }
                    return passesFilter;
                })
                .collect(Collectors.toList());
        System.out.println("Mapped clients: " + mappedClients.size());

        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));

        for (Clients cl : mappedClients) {
            int discount;
            if (currentDate.getMonthValue() == cl.getBirthday().getMonth()) discount = 10;
            else discount = 5;

            if (currentTime.isBefore(LocalTime.of(19, 0))) {
                String messageText = cl.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
                Message message = new Message(cl.getPhone(), messageText);
                kafkaTemplate.send("SMSMassage", message);
            }

            cl.setMessageSend(true);
        }

        clientRepository.saveAll(mappedClients);

        return mappedClients;
    }

    public ClientINFO getClientByID(long id) {
        return clientsFeignClient.getByIdClient(id);
    }
}






