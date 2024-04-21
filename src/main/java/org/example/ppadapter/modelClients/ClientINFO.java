package org.example.ppadapter.modelClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientINFO {
    private Long clientId;
    private String name;
    private String middleName;
    private String surname;
    private int age;
    private Date birthday;
    private String phone;


    public ClientINFO(long clientId, String name, String middleName, String surname, int age, Date birthday, String phone) {
    }
}
