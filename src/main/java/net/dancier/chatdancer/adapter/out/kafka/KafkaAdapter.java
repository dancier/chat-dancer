package net.dancier.chatdancer.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.out.SendChatCreatedDto;
import net.dancier.chatdancer.application.port.out.SendChatCreatedPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaAdapter implements SendChatCreatedPort {

    private final KafkaTemplate kafkaTemplate;

    @Override
    public void send(SendChatCreatedDto sendChatCreatedDto) {
        kafkaTemplate.send("foo-topic", "bar-content");
    }
}
