package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;

import java.util.List;

public interface CreateChatUseCase {

    Chat.ChatId createChat(CreateChatCommand createChatCommand);

}
