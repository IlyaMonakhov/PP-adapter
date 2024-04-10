package org.example.ppadapter.modelClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ClientINFO {
    private String clientId;
    private String name;
    private String middleName;
    private String surname;
    private int age;
    private LocalDate birthday;
    private String phone;
}
