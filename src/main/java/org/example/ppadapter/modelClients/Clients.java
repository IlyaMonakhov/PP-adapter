package org.example.ppadapter.modelClients;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "clients")
public class Clients {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "message_send")
    private Boolean messageSend;


    public Clients(Long clientId, String fullName, String phone, Date birthday, Boolean messageSend) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.phone = phone;
        this.birthday = birthday;
        this.messageSend = messageSend;
    }

    public Clients() {

    }
}

