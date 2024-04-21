package org.example.ppadapter.service;

import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.mapper.DTOMap;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.modelClients.Message;
import org.example.ppadapter.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientService {

    private final ClientsFeignClient clientsFeignClient;
    private final DTOMap dtoMapper;
    private final ClientRepository clientRepository;
    private final KafkaTemplate<String, Message> kafkaTemplate;


    @Autowired
    public ClientService(ClientsFeignClient clientsFeignClient, DTOMap dtoMapper, ClientRepository clientRepository, KafkaTemplate<String, Message> kafkaTemplate) {
        this.clientsFeignClient = clientsFeignClient;
        this.dtoMapper = dtoMapper;
        this.clientRepository = clientRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

//        @Scheduled(fixedRate = 100000)
//    public void all() {
//        clientRepository.saveAll(clientsFeignClient.allGetClients()
//                .stream()
//                .map(dtoMapper::map)
//                .collect(Collectors.toList()));
//    }

    public List<Clients> getAll() {
        List<ClientINFO> allClients = clientsFeignClient.allGetClients();
        System.out.println("Received clients: " + allClients.size());

        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Moscow"));
        List<Clients> mappedClients = allClients.stream()
                .map(dtoMapper::map)
                .filter(ms -> ms.getPhone().endsWith("7"))
                .filter(ms -> ms.getBirthday().toLocalDate().getMonthValue() == LocalDate.now().getMonthValue())
                .collect(Collectors.toList());

        System.out.println("Mapped clients: " + mappedClients.size());

        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));

        for (Clients cl : mappedClients) {
            int discount = currentDate.getMonthValue() == cl.getBirthday().toLocalDate().getMonthValue() ? 10 : 5;

            if (currentTime.isBefore(LocalTime.of(19, 0))) {
                String messageText = cl.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
                Message message = new Message(cl.getPhone(), messageText);
                kafkaTemplate.send("SMSMassage", message);
                cl.setMessageSend(true);
            }
        }

        // Сохраняем изменения в базе данных

        clientRepository.saveAll(mappedClients);


        return mappedClients;
    }



    public Clients clientById(Long clientId) {
        ClientINFO clientInfo = clientsFeignClient.clientById(clientId);
        Clients client = dtoMapper.map(clientInfo);

        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Moscow"));
        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));

        if (client.getPhone().endsWith("7") && client.getBirthday().toLocalDate().getMonthValue() == currentDate.getMonthValue()) {
            int discount = currentDate.getMonthValue() == client.getBirthday().toLocalDate().getMonthValue() ? 10 : 5;

            if (currentTime.isBefore(LocalTime.of(19, 0))) {
                String messageText = client.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
                Message message = new Message(client.getPhone(), messageText);
                kafkaTemplate.send("SMSMassage", message);
                client.setMessageSend(true);
            }
        }

        // Сохраняем изменения в базе данных
        clientRepository.save(client);

        return client;
    }
    }







