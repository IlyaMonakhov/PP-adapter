package org.example.ppadapter.httpClient;
import org.example.ppadapter.modelClients.ClientINFO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name = "Clients", url = "${clients.url}")
public interface ClientsFeignClient {

    @GetMapping
    List<ClientINFO> allGetClients();


    @GetMapping("/api/v1/getClients/{phone}")
    ClientINFO getByIdClient(@PathVariable("phone") String phone);
}
