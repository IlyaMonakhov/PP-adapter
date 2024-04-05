package org.example.ppadapter.mapper;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public class DTOMap {

    public Clients mapClientInfoToClients(ClientINFO clientINFO) {
        Clients clients = new Clients();
        clients.setFullName(clientINFO.getName() + " " + clientINFO.getMiddleName() + " " + clientINFO.getSurname());
        clients.setPhone(clientINFO.getPhone());
        clients.setBirthday(java.sql.Date.valueOf(clientINFO.getBirthday()));
        clients.setMessageSend(false);

        return clients;
    }
}
