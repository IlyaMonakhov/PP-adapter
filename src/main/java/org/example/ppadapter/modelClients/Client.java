package org.example.ppadapter.modelClients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "clients")
public class Client {


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


}

