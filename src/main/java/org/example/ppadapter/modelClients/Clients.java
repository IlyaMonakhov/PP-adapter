package org.example.ppadapter.modelClients;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Clients")

public class Clients {
    @Column(name = "fullName")
    private String fullName;
    @Id
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "messageSend")
    private Boolean messageSend;
}

