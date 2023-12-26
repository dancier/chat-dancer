package net.dancier.chatdancer.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SendOutboxService {
    static final Logger log = LoggerFactory.getLogger(SendOutboxService.class);

    private final KafkaTemplate kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void send(OutboxJpaEntity entity) throws JsonProcessingException {
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                        .withId(UUID.randomUUID().toString())
                        .withSource(URI.create(entity.getSource()))
                        .withType(entity.getType())
                        .withData(entity.getData().getBytes())
                .build();
        kafkaTemplate.send(entity.getType(),
                entity.getKey(),
                cloudEvent);
    }

}
