package com.github.ashimjk.cloudstreamkafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext
@SpringBootTest
@ContextConfiguration(classes = PayloadApplication.class)
class SendAndReceivePayloadTest {

    @Autowired private Processor processor;
    @Autowired private MessageCollector messageCollector;

    @Test
    public void whenSendMessage_thenResponseShouldUpdateText() {
        processor.input()
                 .send(MessageBuilder.withPayload(new Payload("This is my message"))
                                     .build());

        String json = messageCollector.forChannel(processor.output()).poll().getPayload().toString();

        Payload payload = jsonToObject(json);

        assertEquals("[1]: This is my message", payload.getValue());
    }

    @SneakyThrows
    private Payload jsonToObject(String json) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Payload.class);
    }

}