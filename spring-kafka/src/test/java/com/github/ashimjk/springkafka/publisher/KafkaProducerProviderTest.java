package com.github.ashimjk.springkafka.publisher;

import com.github.ashimjk.springkafka.AbstractKafkaTest;
import com.github.ashimjk.springkafka.config.KafkaProducerConfig;
import com.github.ashimjk.springkafka.model.Payload;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {KafkaProducerConfig.class, KafkaProducerProvider.class})
class KafkaProducerProviderTest extends AbstractKafkaTest {

    private Consumer<String, Payload> consumer;
    @Autowired private KafkaProducerProvider kafkaProducerProvider;

    @BeforeEach
    void setup() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("test_group", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ConsumerFactory<String, Payload> consumerFactory
                = new DefaultKafkaConsumerFactory<>(consumerProps,
                                                    new StringDeserializer(),
                                                    new JsonDeserializer<>(Payload.class, false));

        consumer = consumerFactory.createConsumer();
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, TEST_TOPIC);
    }

    @Test
    @DisplayName("Verify send payload")
    void testSendPayload() {
        Payload payload = new Payload();
        payload.setValue("send test payload");

        kafkaProducerProvider.sendPayload(payload);

        ConsumerRecord<String, Payload> record = KafkaTestUtils.getSingleRecord(consumer, TEST_TOPIC);
        Payload receivedPayload = record.value();

        assertEquals("send test payload", receivedPayload.getValue());
    }

    @AfterEach
    void cleanup() {
        consumer.close();
    }

}