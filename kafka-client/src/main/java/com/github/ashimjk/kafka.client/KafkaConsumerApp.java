package com.github.ashimjk.kafka.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static com.github.ashimjk.kafka.client.KafkaConfig.BOOTSTRAP_SERVERS;
import static com.github.ashimjk.kafka.client.KafkaConfig.TOPIC_NAME;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class KafkaConsumerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerApp.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(GROUP_ID_CONFIG, "replica_topic_group");

        new KafkaTopicApp(BOOTSTRAP_SERVERS).create(TOPIC_NAME);

        receiveMessages(properties);
    }

    private static void receiveMessages(Properties properties) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(List.of(TOPIC_NAME));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
                records.forEach(KafkaConsumerApp::printRecord);
            }
        } catch (Exception exception) {
            LOGGER.error("Failed to consume the message", exception);
        }
    }

    private static void printRecord(ConsumerRecord<String, String> record) {
        System.out.printf("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s%n",
                          record.topic(),
                          record.partition(),
                          record.offset(),
                          record.key(),
                          record.value());
    }

}
