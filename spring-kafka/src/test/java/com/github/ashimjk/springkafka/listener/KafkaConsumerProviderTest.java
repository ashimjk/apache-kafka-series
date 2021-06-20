package com.github.ashimjk.springkafka.listener;

import com.github.ashimjk.springkafka.AbstractKafkaTest;
import com.github.ashimjk.springkafka.config.KafkaConsumerConfig;
import com.github.ashimjk.springkafka.model.Payload;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.awaitility.Durations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {KafkaConsumerConfig.class, KafkaConsumerProvider.class})
class KafkaConsumerProviderTest extends AbstractKafkaTest {

    private Producer<String, Payload> producer;
    @MockBean private PayloadHandler payloadHandler;

    @BeforeEach
    void setup() {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker.getBrokersAsString());

        ProducerFactory<String, Payload> producerFactory
                = new DefaultKafkaProducerFactory<>(producerProps, new StringSerializer(), new JsonSerializer<>());

        producer = producerFactory.createProducer();
    }

    @Test
    @DisplayName("Verify receive payload")
    void testReceivePayload() {
        Payload payload = new Payload();
        payload.setValue("send test payload");

        producer.send(new ProducerRecord<>(TEST_TOPIC, "", payload));

        await().atMost(Durations.TWO_SECONDS).untilAsserted(() -> {
            ArgumentCaptor<Payload> captor = ArgumentCaptor.forClass(Payload.class);

            verify(payloadHandler).execute(captor.capture());

            assertThat(captor.getValue().getValue()).isEqualTo("send test payload");
        });
    }

    @AfterEach
    void cleanup() {
        producer.close();
    }

}