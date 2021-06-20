package com.github.ashimjk.springkafka;

import com.github.ashimjk.springkafka.model.Payload;
import com.github.ashimjk.springkafka.publisher.KafkaProducerProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringKafkaApplication {

    @Bean
    public CommandLineRunner runner(KafkaProducerProvider kafkaProducerProvider) {
        return args -> {
            Payload payload = new Payload();
            payload.setValue("send payload to kafka");
            kafkaProducerProvider.sendPayload(payload);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }

}
