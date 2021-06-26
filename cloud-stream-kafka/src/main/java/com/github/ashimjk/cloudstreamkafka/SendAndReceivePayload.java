package com.github.ashimjk.cloudstreamkafka;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class SendAndReceivePayload {

    @SendTo(Processor.OUTPUT)
    @StreamListener(Processor.INPUT)
    public Payload enrichLogMessage(Payload payload) {
        return new Payload(String.format("[1]: %s", payload.getValue()));
    }

}
