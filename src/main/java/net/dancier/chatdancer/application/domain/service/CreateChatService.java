package net.dancier.chatdancer.application.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.CreateChatCommand;
import net.dancier.chatdancer.application.port.in.CreateChatUseCase;
import net.dancier.chatdancer.application.port.out.SendChatCreatedEventDto;
import net.dancier.chatdancer.application.port.out.SendChatCreatedEventPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CreateChatService implements CreateChatUseCase {

    private final static Logger log = LoggerFactory.getLogger(CreateChatService.class);

    private final UpdateChatPort updateChatPort;

    private final SendChatCreatedEventPort sendChatCreatedEventPort;

    @Override
    @Transactional
    public Chat.ChatId createChat(CreateChatCommand createChatCommand) {
        System.out.println("Creating Chat");
        Chat chat = Chat.withoutId(LocalDateTime.now());
        createChatCommand.participants().forEach(p -> chat.addParticipant(p));

        Chat.ChatId chatId = updateChatPort.updateChat(chat);

        SendChatCreatedEventDto sendChatCreatedEventDto = new SendChatCreatedEventDto();

        sendChatCreatedEventDto.setParticipantIds(createChatCommand.participants());
        try {
            sendChatCreatedEventPort.send(sendChatCreatedEventDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return chatId;
    }
}
