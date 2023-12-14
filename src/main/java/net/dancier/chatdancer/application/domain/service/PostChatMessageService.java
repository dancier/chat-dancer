package net.dancier.chatdancer.application.domain.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.PostChatMessageCommand;
import net.dancier.chatdancer.application.port.in.PostChatMessageUseCase;
import net.dancier.chatdancer.application.port.out.LoadChatPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class PostChatMessageService implements PostChatMessageUseCase {
    Logger log = LoggerFactory.getLogger(PostChatMessageService.class);

    private final LoadChatPort loadChatPort;

    private final UpdateChatPort updateChatPort;


    @Override
    public void post(PostChatMessageCommand command) {
        log.info("Posting: " + command);
        Chat chat = loadChatPort.loadChat(command.chatId());
        Message message = Message.withoutId(command.text(),command.authorId());
        chat.addMessage(message);
        updateChatPort.updateChat(chat);
    }
}
