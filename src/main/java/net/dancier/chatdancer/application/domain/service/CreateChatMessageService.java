package net.dancier.chatdancer.application.domain.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.CreateChatMessageCommand;
import net.dancier.chatdancer.application.port.in.CreateChatMessageUseCase;
import net.dancier.chatdancer.application.port.out.GetChatPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CreateChatMessageService implements CreateChatMessageUseCase {
    Logger log = LoggerFactory.getLogger(CreateChatMessageService.class);

    private final GetChatPort getChatPort;

    private final UpdateChatPort updateChatPort;


    @Override
    public void create(CreateChatMessageCommand command) {
        log.info("Posting: " + command);
        Chat chat = getChatPort.get(command.chatId());
        Message message = Message.withoutId(command.text(),command.authorId());
        chat.addMessage(message);
        updateChatPort.updateChat(chat);
    }
}