package org.example.ppadapter.service;


import lombok.RequiredArgsConstructor;
import org.example.ppadapter.modelClients.ClientINFO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private ClientINFO clientINFO;
    private final KafkaTemplate<Object, Object> kafkaTemplate;


    public void sendDiscountMessage() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDate = currentTime.toLocalDate();
        LocalDate birthday = clientINFO.getBirthday();
        if (currentTime.getHour() < 19) {
            if (birthday.getDayOfMonth() == currentDate.getDayOfMonth() && birthday.getMonth() == currentDate.getMonth()) {
                String massage10 = "phone: " + clientINFO.getPhone() + "\n" + clientINFO.getName() + clientINFO.getMiddleName() + ", в этом месяце для вас действует скидка 10%";
                kafkaTemplate.send("SMSMassage", massage10);
            } else {
                String massage5 = "phone: " + clientINFO.getPhone() + "\n" + clientINFO.getName() + clientINFO.getMiddleName() + ", в этом месяце для вас действует скидка 5%";
                kafkaTemplate.send("SMSMassage", massage5);
            }
        }
    }
}
