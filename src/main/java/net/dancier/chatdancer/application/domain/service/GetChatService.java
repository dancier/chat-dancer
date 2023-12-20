package net.dancier.chatdancer.application.domain.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.GetChatUseCase;
import net.dancier.chatdancer.application.port.out.GetChatPort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetChatService implements GetChatUseCase {

    private final GetChatPort getChatPort;

    @Override
    public Chat get(Chat.ChatId id) {
        return getChatPort.get(id);
    }
}
