package net.dancier.chatdancer.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.out.SendChatCreatedDto;
import net.dancier.chatdancer.application.port.out.SendEventWhenChatCreatedPort;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Component
public class SendMessagesAdapter implements SendEventWhenChatCreatedPort {
    private static final Logger log = LoggerFactory.getLogger(SendMessagesAdapter.class);

    private final OutboxJpaRepository outboxJpaRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void send(SendChatCreatedDto sendChatCreatedDto) throws JsonProcessingException {
        log.info("sending message");
        String payload = objectMapper.writeValueAsString(sendChatCreatedDto);
        log.info("With payload: " + payload);
        OutboxJpaEntity outboxJpaEntity = new OutboxJpaEntity();
        outboxJpaEntity.setMetaData(payload);
        if (sendChatCreatedDto.getChatId() != null) {
            outboxJpaEntity.setKey(sendChatCreatedDto.getChatId().toString());
        }
        outboxJpaEntity.setPayload(payload);
        outboxJpaEntity.setTopic("sccd");
        outboxJpaEntity.setCreatedAt(OffsetDateTime.now());
        outboxJpaEntity.setStatus(OutboxJpaEntity.STATUS.NEW);
        outboxJpaRepository.save(outboxJpaEntity);
    }

}
