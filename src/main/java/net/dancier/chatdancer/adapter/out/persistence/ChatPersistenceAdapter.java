package net.dancier.chatdancer.adapter.out.persistence;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.out.LoadChatPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatPersistenceAdapter implements LoadChatPort, UpdateChatPort {

    public static final Logger log = LoggerFactory.getLogger(ChatPersistenceAdapter.class);

    @Override
    public Chat loadChat(Chat.ChatId chatId) {
        return null;
    }

    @Override
    public void updateChat(Chat chat) {

    }
}
