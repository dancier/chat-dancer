package net.dancier.chatdancer.application.port.out;

import net.dancier.chatdancer.application.domain.model.Chat;

public interface LoadChatPort {

    Chat loadChat(Chat.ChatId chatId);

}
