package org.example.ppadapter.service;

import lombok.AllArgsConstructor;
import org.example.ppadapter.httpClient.ClientsFeignClient;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.example.ppadapter.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientsFeignClient clientsFeignClient;
    private ClientRepository clientRepository;


    public List<Clients> getAll() {
        List<ClientINFO> allClients = clientsFeignClient.allGetClients();

        LocalDate currentDate = LocalDate.now();

        List<ClientINFO> filterList = allClients.stream()
                .filter(cl -> cl.getPhone().endsWith("7"))
                .filter(cl -> cl.getBirthday().getMonth() == currentDate.getMonth())
                .collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<Clients>>() {
        }.getType();
        List<Clients> clientsToSave = modelMapper.map(filterList, listType);
        clientRepository.saveAll(clientsToSave);


        return clientsToSave;
    }

    public ClientINFO getClientByID(long id) {
        return clientsFeignClient.getByIdClient(id);
    }
}






