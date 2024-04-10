package org.example.ppadapter.httpClient;
import org.example.ppadapter.modelClients.ClientINFO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(value = "UserApi", url = "${clients.url}")
public interface ClientsFeignClient {

    @GetMapping("/api/v1/getClient")
    List<ClientINFO> allGetClients();

    @GetMapping("/api/v1/getClient/{clientId}")
    ClientINFO getByIdClient(@PathVariable("clientId") Long id);


}
