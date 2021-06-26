package com.github.ashimjk.cloudstreamkafka;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PayloadController {

    private final PayloadBinding payloadBinding;

    @SneakyThrows
    @GetMapping
    public void sendMessage(@RequestParam String message) {
        Payload payload = new Payload(message);
        payloadBinding.output().send(MessageBuilder.withPayload(payload).build());
    }

}
