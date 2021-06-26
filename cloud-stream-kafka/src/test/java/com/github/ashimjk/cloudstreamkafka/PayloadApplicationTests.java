package com.github.ashimjk.cloudstreamkafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = PayloadApplication.class)
class PayloadApplicationTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private PayloadBinding payloadBinding;
    @Autowired private MessageCollector messageCollector;

    @Test
    public void sendAndReceiveMessage() throws Exception {

        mockMvc.perform(get("/")
                                .queryParam("message", "This is my message"))
               .andExpect(status().isOk());

        String json = messageCollector.forChannel(payloadBinding.output()).poll().getPayload().toString();

        Payload payload = jsonToObject(json);

        assertEquals("This is my message", payload.getValue());
    }

    @SneakyThrows
    private Payload jsonToObject(String json) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Payload.class);
    }

}
