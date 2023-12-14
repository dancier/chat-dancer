package net.dancier.chatdancer.application.domain.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.MessagesByChatUseCase;
import net.dancier.chatdancer.application.port.out.LoadChatPort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MessagesByChatService implements MessagesByChatUseCase {

    private final LoadChatPort loadChatPort;

    @Override
    public List<Message> byChatId(Chat.ChatId chatId) {
        return loadChatPort.loadChat(chatId).getMessages();
    }
}
