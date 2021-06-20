package com.github.ashimjk.springkafka.listener;

import com.github.ashimjk.springkafka.model.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PayloadHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadHandler.class);

    public void execute(Payload payload) {
        LOGGER.info("Payload = {}", payload);
    }

}
