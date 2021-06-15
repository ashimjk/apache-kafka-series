package com.github.ashimjk.kafka.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class KafkaTopicAppTest {

    @Container
    private static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:5.4.3")
    );

    private KafkaTopicApp kafkaTopicApp;

    @BeforeEach
    void setup() {
        kafkaTopicApp = new KafkaTopicApp(KAFKA_CONTAINER.getBootstrapServers());
    }

    @Test
    @DisplayName("Verify topic creation")
    void testCreateTopic() throws Exception {
        kafkaTopicApp.create("my_topic");

        String topicCommand = "/usr/bin/kafka-topics --bootstrap-server=localhost:9092 --list";
        String result = KAFKA_CONTAINER.execInContainer("/bin/sh", "-c", topicCommand).getStdout();

        assertThat(result).contains("my_topic");
    }

}