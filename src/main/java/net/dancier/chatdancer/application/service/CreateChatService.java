package net.dancier.chatdancer.application.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.CreateChatCommand;
import net.dancier.chatdancer.application.port.in.CreateChatUseCase;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CreateChatService implements CreateChatUseCase {

    private final UpdateChatPort updateChatPort;

    @Override
    public Chat.ChatId createChat(CreateChatCommand createChatCommand) {
        System.out.println("Creating Chat");
        Chat chat = new Chat();
        chat.setChatParticipants(createChatCommand.participants());
        updateChatPort.updateChat(chat);
        return new Chat.ChatId(UUID.randomUUID());
    }
}
