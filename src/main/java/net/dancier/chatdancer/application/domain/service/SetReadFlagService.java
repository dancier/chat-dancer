package net.dancier.chatdancer.application.domain.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.SetReadFlagUseCase;
import net.dancier.chatdancer.application.port.out.MessageAndChatIdDto;
import net.dancier.chatdancer.application.port.out.MessagePort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SetReadFlagService implements SetReadFlagUseCase {

    private final MessagePort messagePort;

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
    }
}
