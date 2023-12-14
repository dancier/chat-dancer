package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

public record PostChatMessageCommand (
        String text,
        Message.AuthorId authorId,
        Chat.ChatId chatId
    ) {
}
