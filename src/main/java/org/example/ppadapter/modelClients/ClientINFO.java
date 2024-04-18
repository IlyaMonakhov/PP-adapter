package org.example.ppadapter.modelClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Component
public class ClientINFO {
    private String clientId;
    private String name;
    private String middleName;
    private String surname;
    private int age;
    private Date birthday;
    private String phone;
}
