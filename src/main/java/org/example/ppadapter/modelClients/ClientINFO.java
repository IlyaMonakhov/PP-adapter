package org.example.ppadapter.modelClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientINFO {
        private String clientId;
        private String name;
        private String middleName;
        private String surname;
        private int age;
        private LocalDate birthday;
        private String phone;

}
