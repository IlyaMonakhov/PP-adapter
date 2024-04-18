package org.example.ppadapter.mapper;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.modelClients.Clients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DTOMap {

    default String createFullName(String name, String middleName, String surname) {
        return name + " " + middleName + " " + surname;
    }

    @Mapping(target = "fullName", expression = "java(createFullName(clientINFO.getName(), clientINFO.getMiddleName(), clientINFO.getSurname()))")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "messageSend", constant = "false")
    Clients map(ClientINFO clientINFO);
}
