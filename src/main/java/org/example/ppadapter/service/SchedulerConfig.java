package org.example.ppadapter.service;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final ClientService clientService;


    public SchedulerConfig(ClientService clientService, KafkaProducer kafkaProducer) {
        this.clientService = clientService;

    }

    @Scheduled(cron = "0 0 * * * ?", zone = "Europe/Moscow")
    public void sendSmsMessages() {
        clientService.getAll();


    }
}
