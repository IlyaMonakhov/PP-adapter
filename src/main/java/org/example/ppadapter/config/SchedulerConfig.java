package org.example.ppadapter.config;
import org.example.ppadapter.modelClients.ClientINFO;
import org.example.ppadapter.service.ClientService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerConfig {
    private ClientINFO clientINFO;

    private final ClientService clientService;

    public SchedulerConfig(ClientService clientService) {
        this.clientService = clientService;
    }


    @Scheduled(cron = "0 0 * * * ?", zone = "Europe/Moscow")
    public void sendSmsMessages() {
        clientService.getAll();


    }
}
