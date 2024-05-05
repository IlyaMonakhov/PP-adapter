package org.example.ppadapter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "My API", version = "1.0", description = "API Information"))
public class PpAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpAdapterApplication.class, args);
    }

}
