package net.dancier.chatdancer.application.domain.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.GetMessagesByChatUseCase;
import net.dancier.chatdancer.application.port.out.GetChatPort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetMessagesByChatService implements GetMessagesByChatUseCase {

    private final GetChatPort getChatPort;

    @Override
    public List<Message> byChatId(Chat.ChatId chatId) {
        return getChatPort.get(chatId).getMessages();
    }
}
