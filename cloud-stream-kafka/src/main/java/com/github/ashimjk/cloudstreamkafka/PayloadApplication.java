package com.github.ashimjk.cloudstreamkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
@EnableBinding({PayloadBinding.class, Processor.class})
public class PayloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayloadApplication.class, args);
    }

}
