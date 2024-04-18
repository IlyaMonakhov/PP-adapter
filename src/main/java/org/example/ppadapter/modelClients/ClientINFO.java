package org.example.ppadapter.modelClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientINFO {
    private String clientId;
    private String name;
    private String middleName;
    private String surname;
    private int age;
    private Date birthday;
    private String phone;
}
