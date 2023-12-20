package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

import java.util.List;

public interface GetMessagesByChatUseCase {
    List<Message> byChatId(Chat.ChatId chatId);
}
