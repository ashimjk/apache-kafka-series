package com.github.ashimjk.springkafka.publisher;

import com.github.ashimjk.springkafka.model.Payload;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
public class KafkaProducerProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerProvider.class);

    @Value(value = "${kafka.topic}")
    private String topic;
    private final KafkaTemplate<String, Payload> kafkaTemplate;

    public void sendPayload(Payload payload) {
        ListenableFuture<SendResult<String, Payload>> future = kafkaTemplate.send(topic, payload);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Payload> result) {
                LOGGER.info("Sent payload=[{}] with offset=[{}]", payload, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to send payload=[{}}] due to : ", payload, ex);
            }
        });
    }

}