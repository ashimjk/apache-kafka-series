package com.github.ashimjk.springkafka;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext
@ActiveProfiles("manual")
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, topics = "test_topic")
@TestPropertySource(properties = {"kafka.topic=test_topic", "kafka.bootstrapAddresses=${spring.embedded.kafka.brokers}"})
public abstract class AbstractKafkaTest {

    protected static final String TEST_TOPIC = "test_topic";

    @Autowired protected EmbeddedKafkaBroker embeddedKafkaBroker;

}
