package com.github.ashimjk.springkafka.config;

import com.github.ashimjk.springkafka.model.Payload;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Profile("manual")
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddresses}")
    private String bootstrapAddresses;

    @Value(value = "${kafka.consumer.groupId}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, Payload> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddresses);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props,
                                                 new StringDeserializer(),
                                                 new JsonDeserializer<>(Payload.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payload> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Payload> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}