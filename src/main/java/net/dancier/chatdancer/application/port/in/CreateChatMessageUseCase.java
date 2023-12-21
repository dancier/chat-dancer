package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Message;

public interface CreateChatMessageUseCase {

    Message.MessageId create(CreateChatMessageCommand command);

}
