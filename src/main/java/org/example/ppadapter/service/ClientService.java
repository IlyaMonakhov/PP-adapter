package org.example.ppadapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.mapper.DtoMapper;
import org.example.ppadapter.modelClients.Client;
import org.example.ppadapter.modelClients.ClientINFO;
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
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientsFeignClient clientsFeignClient;
    private final DtoMapper dtoMapper;
    private final ClientRepository clientRepository;
    private final KafkaTemplate<String, Message> kafkaTemplate;


    public List<Client> getAll() {
        List<ClientINFO> allClients = clientsFeignClient.allGetClients();
        log.info("Received clients: {}", allClients.size());

        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Moscow"));
        List<Client> mappedClients = allClients.stream()
                .map(dtoMapper::map)
                .filter(mappedClient -> mappedClient.getPhone().endsWith("7"))
                .filter(mappedClient -> mappedClient.getBirthday() != null &&
                        mappedClient.getBirthday().toLocalDate().getMonthValue() == LocalDate.now().getMonthValue())
                .collect(Collectors.toList());

        log.info("Mapped clients: {}", mappedClients.size());

        LocalTime currentTime = LocalTime.now(ZoneId.of("Europe/Moscow"));

        for (Client cl : mappedClients) {
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



    public Client clientById(Long clientId) {
        ClientINFO clientInfo = clientsFeignClient.clientById(clientId);
        Client client = dtoMapper.map(clientInfo);

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



