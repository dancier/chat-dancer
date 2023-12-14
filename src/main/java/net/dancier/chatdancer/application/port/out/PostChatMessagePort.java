package net.dancier.chatdancer.application.port.out;

import net.dancier.chatdancer.application.domain.model.Message;

public interface PostChatMessagePort {

    Message.MessageId postChatMessage(Message message);

}
