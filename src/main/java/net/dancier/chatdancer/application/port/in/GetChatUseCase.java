package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;

public interface GetChatUseCase {
    Chat get(Chat.ChatId id);

}
