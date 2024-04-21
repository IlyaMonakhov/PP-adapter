//package org.example.ppadapter.config;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.example.ppadapter.modelClients.Message;
//import org.springframework.kafka.core.ProducerFactory;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ConfigKafka {
//
//
//    @Bean
//    public ProducerFactory<Object, Message> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//
//
//    @Bean
//    public NewTopic newTopic() {
//        return new NewTopic("SMSMassage", 1, (short) 1);
//    }
//
//
//    @Bean
//    public KafkaTemplate<Object, Message> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//}
