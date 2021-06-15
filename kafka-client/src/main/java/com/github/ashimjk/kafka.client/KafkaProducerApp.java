package com.github.ashimjk.kafka.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.stream.IntStream;

import static com.github.ashimjk.kafka.client.KafkaConfig.BOOTSTRAP_SERVERS;
import static com.github.ashimjk.kafka.client.KafkaConfig.TOPIC_NAME;
import static java.util.stream.Collectors.toMap;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class KafkaProducerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerApp.class);

    public static void main(String[] args) {
        Map<String, String> messages = IntStream.range(0, 200)
                                                .boxed()
                                                .collect(toMap(String::valueOf, i -> "Message : " + i));

        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        new KafkaTopicApp(BOOTSTRAP_SERVERS).create(TOPIC_NAME);

        sendMessages(properties, messages);
    }

    private static void sendMessages(Properties properties, Map<String, String> messages) {

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {

            messages.entrySet()
                    .stream()
                    .map(p -> new ProducerRecord<>(TOPIC_NAME, p.getKey(), p.getValue()))
                    .forEach(producer::send);

        } catch (Exception ex) {
            LOGGER.error("Failed to send the message", ex);
        }
    }

}
