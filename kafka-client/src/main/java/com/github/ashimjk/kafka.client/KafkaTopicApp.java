package com.github.ashimjk.kafka.client;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTopicApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicApp.class);

    private final Properties properties = new Properties();

    public KafkaTopicApp(String bootstrapServers) {
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    }

    public void create(String topicName) {
        try (Admin admin = Admin.create(properties)) {
            int numOfPartitions = 1;
            short replicationFactor = 1;
            NewTopic newTopic = new NewTopic(topicName, numOfPartitions, replicationFactor);

            CreateTopicsResult topicsResult = admin.createTopics(Collections.singleton(newTopic));

            KafkaFuture<Void> kafkaFuture = topicsResult.values().get(topicName);
            kafkaFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

}