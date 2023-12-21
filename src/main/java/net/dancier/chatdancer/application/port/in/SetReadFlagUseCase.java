package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

public interface SetReadFlagUseCase {

    public void setReadFlag(Message.MessageId messageId, Chat.ParticipantId participantId,  Boolean read);
}
