package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Message;

public interface PostChatMessageUseCase {

    void post(PostChatMessageCommand command);

}
