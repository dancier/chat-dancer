package net.dancier.chatdancer.application.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.CreateChatCommand;
import net.dancier.chatdancer.application.port.in.CreateChatUseCase;
import net.dancier.chatdancer.application.port.out.SendChatCreatedDto;
import net.dancier.chatdancer.application.port.out.SendEventWhenChatCreatedPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CreateChatService implements CreateChatUseCase {

    private final static Logger log = LoggerFactory.getLogger(CreateChatService.class);

    private final UpdateChatPort updateChatPort;

    private final SendEventWhenChatCreatedPort sendEventWhenChatCreatedPort;

    @Override
    @Transactional
    public Chat.ChatId createChat(CreateChatCommand createChatCommand) {
        System.out.println("Creating Chat");
        Chat chat = Chat.withoutId(LocalDateTime.now());
        createChatCommand.participants().forEach(p -> chat.addParticipant(p));

        Chat.ChatId chatId = updateChatPort.updateChat(chat);

        SendChatCreatedDto sendChatCreatedDto = new SendChatCreatedDto();

        sendChatCreatedDto.setParticipantIds(createChatCommand.participants());
        try {
            sendEventWhenChatCreatedPort.send(sendChatCreatedDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return chatId;
    }
}
