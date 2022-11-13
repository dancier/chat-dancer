package net.dancier.chatdancer.services;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.dao.ChatDaoInMemory;
import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.models.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatDaoInMemory chatDaoInMemory;
    public Chat createNewChat(Chat chat) {
        // sending an event to kafka
        return chatDaoInMemory.createNewChatByParticipants(chat);
    }


    public List<Chat> getAllChatsForUser(UUID id) {

        return chatDaoInMemory.getAllChatsForUser(id);
    }

    public Chat getChatById(UUID chatId) {
        return chatDaoInMemory.getChatById(chatId);
    }

    public List<Message> getAllMessagesForChat(UUID chatId) {
        return chatDaoInMemory.getAllMessagesForChat(chatId);
    }

    public Message createMessageForChat(Message message, UUID chatId) {
        return chatDaoInMemory.createNewMessageForChat(message, chatId);
    }

}
