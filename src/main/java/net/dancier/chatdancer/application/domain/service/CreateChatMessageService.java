package net.dancier.chatdancer.application.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.exception.ApplicationException;
import net.dancier.chatdancer.application.port.in.CreateChatMessageCommand;
import net.dancier.chatdancer.application.port.in.CreateChatMessageUseCase;
import net.dancier.chatdancer.application.port.out.GetChatPort;
import net.dancier.chatdancer.application.port.out.SendMessageCreatedEventPort;
import net.dancier.chatdancer.application.port.out.ChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CreateChatMessageService implements CreateChatMessageUseCase {
    Logger log = LoggerFactory.getLogger(CreateChatMessageService.class);

    private final GetChatPort getChatPort;

    private final ChatPort chatPort;

    private final SendMessageCreatedEventPort sendMessageCreatedEventPort;

    @Override
    public void create(CreateChatMessageCommand command) {
        log.info("Posting: " + command);
        Chat chat = getChatPort.get(command.chatId());
        Message message = Message.withoutId(command.text(),command.authorId());
        chat.addMessage(message);
        chatPort.updateChat(chat);

        try {
            sendMessageCreatedEventPort.send(chat, message);
        } catch (JsonProcessingException jpe) {
            log.error("Unable to process " + command);
            throw new ApplicationException("Unable to process...", jpe);
        }
    }
}
