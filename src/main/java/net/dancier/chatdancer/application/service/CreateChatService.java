package net.dancier.chatdancer.application.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.CreateChatCommand;
import net.dancier.chatdancer.application.port.in.CreateChatUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CreateChatService implements CreateChatUseCase {

    @Override
    public Chat.ChatId createChat(CreateChatCommand createChatCommand) {
        System.out.println("Creating Chat");
        return new Chat.ChatId(UUID.randomUUID());
    }
}
