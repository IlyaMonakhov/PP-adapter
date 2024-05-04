package org.example.ppadapter.modelClients;

import lombok.Data;

@Data
public class Message {

    private String phone;
    private String messageText;


    public Message(String phone, String messageText) {
        this.phone = phone;
        this.messageText = messageText;
    }

    public Message() {

    }
}

