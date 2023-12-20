package net.dancier.chatdancer.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.out.SendChatCreatedEventDto;
import net.dancier.chatdancer.application.port.out.SendChatCreatedEventPort;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Component
public class ScheduleMessagesAdapter implements SendChatCreatedEventPort {
    private static final Logger log = LoggerFactory.getLogger(ScheduleMessagesAdapter.class);

    private final OutboxJpaRepository outboxJpaRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void send(SendChatCreatedEventDto sendChatCreatedEventDto) throws JsonProcessingException {
        log.info("scheduling the sending of the message in database:  " + sendChatCreatedEventDto);
        String payload = objectMapper.writeValueAsString(sendChatCreatedEventDto);
        OutboxJpaEntity outboxJpaEntity = new OutboxJpaEntity();
        outboxJpaEntity.setMetaData(payload);
        if (sendChatCreatedEventDto.getChatId() != null) {
            outboxJpaEntity.setKey(sendChatCreatedEventDto.getChatId().toString());
        }
        outboxJpaEntity.setPayload(payload);
        outboxJpaEntity.setTopic("sccd");
        outboxJpaEntity.setCreatedAt(OffsetDateTime.now());
        outboxJpaEntity.setStatus(OutboxJpaEntity.STATUS.NEW);
        outboxJpaRepository.save(outboxJpaEntity);
    }

}
