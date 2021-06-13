package com.github.ashimjk.kafka.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerApp.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9090,localhost:9091");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            for (int i = 0; i < 200; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        "replica_topic",
                        Integer.toString(i),
                        "Message : " + i
                );

                producer.send(record);
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to send the message", ex);
        }

    }

}
