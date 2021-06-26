package com.github.ashimjk.cloudstreamkafka;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class PayloadListener {

    @StreamListener(PayloadBinding.INPUT)
    public void listen(Payload payload) {
        System.out.println("payload = " + payload);
    }

}
