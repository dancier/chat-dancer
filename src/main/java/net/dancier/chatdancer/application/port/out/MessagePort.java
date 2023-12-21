package net.dancier.chatdancer.application.port.out;

import net.dancier.chatdancer.application.domain.model.Message;

public interface MessagePort {

    MessageAndChatIdDto loadMessage(Message.MessageId messageId);

    void update(MessageAndChatIdDto messageAndChatIdDto);
}
