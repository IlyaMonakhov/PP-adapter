package org.example.ppadapter.httpClient;
import org.example.ppadapter.modelClients.ClientInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@FeignClient(name = "Clients", url = "${clients.url}")
public interface ClientsFeignClient {

    @GetMapping("/api/v1/getClients")
    List<ClientInfo> allGetClients();

    @PostMapping("/api/v1/getClients/{clientId}")
    ClientInfo clientById(@PathVariable Long clientId);
}
