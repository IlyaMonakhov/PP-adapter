package org.example.ppadapter.modelClients;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class Message {

    private final String phone;
    private final String messageText;


}

