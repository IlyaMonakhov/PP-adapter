package org.example.ppadapter.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
public class KafkaSMSService {


    @Bean
    public NewTopic newTopic() {
        return new NewTopic("SMSMassage", 1, (short) 1);
    }

}