package net.dancier.chatdancer.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.out.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ScheduleMessagesAdapter implements
        SendChatCreatedEventPort,
        SendMessageCreatedEventPort,
        SendReadFlagUpdatedEventPort
{
    private static final Logger log = LoggerFactory.getLogger(ScheduleMessagesAdapter.class);
    private static final String SOURCE = "http://chat-dancer.dancier.net";

    private final OutboxJpaRepository outboxJpaRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void send(SendChatCreatedEventDto sendChatCreatedEventDto) throws JsonProcessingException {
        log.info("Scheduling Business Event for:  " + sendChatCreatedEventDto);
        String data = objectMapper.writeValueAsString(sendChatCreatedEventDto);
        OutboxJpaEntity outboxJpaEntity = new OutboxJpaEntity();
        outboxJpaEntity.setData(data);
        outboxJpaEntity.setType(TopicNames.CHAT_CREATED);
        outboxJpaEntity.setCreatedAt(LocalDateTime.now());
        outboxJpaEntity.setStatus(OutboxJpaEntity.STATUS.NEW);
        outboxJpaEntity.setKey(sendChatCreatedEventDto.getChatId().toString());
        outboxJpaEntity.setSource(SOURCE);
        outboxJpaRepository.save(outboxJpaEntity);
    }

    @Override
    public void send(Chat chat, Message message) throws JsonProcessingException {
        SendMessageCreatedEventDto sendMessageCreatedEventDto = new SendMessageCreatedEventDto();

        sendMessageCreatedEventDto.setMessageId(message.getId().getValue());
        sendMessageCreatedEventDto.setText(message.getText());
        sendMessageCreatedEventDto.setCreatedAt(OffsetDateTime.of(message.getCreatedAt(), ZoneOffset.UTC));
        sendMessageCreatedEventDto.setChatId(chat.getChatId().getId());
        sendMessageCreatedEventDto.setAuthorId(message.getAuthorId().getValue());
        sendMessageCreatedEventDto.setParticipantIds(chat.getChatParticipants().stream().map(Chat.ParticipantId::getId).collect(Collectors.toList()));
        
        log.info("Scheduling Business Event for:  " +  sendMessageCreatedEventDto);
        String data = objectMapper.writeValueAsString(sendMessageCreatedEventDto);
        OutboxJpaEntity outboxJpaEntity = new OutboxJpaEntity();
        outboxJpaEntity.setKey(sendMessageCreatedEventDto.getChatId().toString());
        outboxJpaEntity.setData(data);
        outboxJpaEntity.setType(TopicNames.MESSAGE_POSTED);
        outboxJpaEntity.setCreatedAt(LocalDateTime.now());
        outboxJpaEntity.setStatus(OutboxJpaEntity.STATUS.NEW);
        outboxJpaEntity.setSource(SOURCE);
        outboxJpaRepository.save(outboxJpaEntity);
    }

    @Override
    public void send(SendReadFlagUpdatedEventDto sendReadFlagUpdatedEventDto) throws JsonProcessingException {
        String data = objectMapper.writeValueAsString(sendReadFlagUpdatedEventDto);
        OutboxJpaEntity outboxJpaEntity = new OutboxJpaEntity();
        outboxJpaEntity.setKey(sendReadFlagUpdatedEventDto.getReaderId());
        outboxJpaEntity.setData(data);
        outboxJpaEntity.setType(TopicNames.MESSAGE_READ);
        outboxJpaEntity.setCreatedAt(LocalDateTime.now());
        outboxJpaEntity.setStatus(OutboxJpaEntity.STATUS.NEW);
        outboxJpaEntity.setSource(SOURCE);
        outboxJpaRepository.save(outboxJpaEntity);
    }
}
