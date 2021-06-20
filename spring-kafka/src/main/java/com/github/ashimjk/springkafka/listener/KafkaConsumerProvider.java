package com.github.ashimjk.springkafka.listener;

import com.github.ashimjk.springkafka.model.Payload;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerProvider.class);

    public final PayloadHandler payloadHandler;

    public KafkaConsumerProvider(PayloadHandler payloadHandler) {
        this.payloadHandler = payloadHandler;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void receive(ConsumerRecord<?, ?> consumerRecord, Payload payload) {
        LOGGER.info("Received consumer payload='{}'", consumerRecord.toString());

        this.payloadHandler.execute(payload);
    }

}