package net.dancier.chatdancer.application.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.exception.ApplicationException;
import net.dancier.chatdancer.application.port.in.SetReadFlagUseCase;
import net.dancier.chatdancer.application.port.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SetReadFlagService implements SetReadFlagUseCase {

    private static final Logger log = LoggerFactory.getLogger(SetReadFlagService.class);

    private final MessagePort messagePort;
    private final SendReadFlagUpdatedEventPort sendReadFlagUpdatedEventPort;

    @Override
    public void setReadFlag(Message.MessageId messageId, Chat.ParticipantId participantId, Boolean read) {

        MessageAndChatIdDto messageAndChatIdDto = messagePort.loadMessage(messageId);
        Message message = messageAndChatIdDto.getMessage();
        if (read) {
            message.getReadBy().add(participantId);
        } else {
            if (message.getReadBy().contains(participantId)) {
                message.getReadBy().remove(participantId);
            }
        }
        messagePort.update(messageAndChatIdDto);

        SendReadFlagUpdatedEventDto sendReadFlagUpdatedEventDto = new SendReadFlagUpdatedEventDto();
        sendReadFlagUpdatedEventDto.setMessageId(messageId.getValue().toString());
        sendReadFlagUpdatedEventDto.setReaderId(participantId.getId());
        sendReadFlagUpdatedEventDto.setRead(read);
        try {
            sendReadFlagUpdatedEventPort.send(sendReadFlagUpdatedEventDto);
        } catch (JsonProcessingException jpe) {
            log.error("Unable to send setReadFlag Event." + jpe);
            throw new ApplicationException("Unable to process.", jpe);
        }
    }
}
