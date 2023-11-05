package net.dancier.chatdancer.services;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.dao.ChatDao;
import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatDao chatDao;

    public Chat createNewChat(Chat chat) {
        // sending an event to kafka

        return chatDao.createNewChat(chat);
    }


    public List<Chat> getAllChatsForDancer(UUID id) {
        return chatDao.getAllChatsForDancer(id);
    }

    public Chat getChatById(UUID chatId) {
        return chatDao.getChatById(chatId);
    }

    public List<Message> getAllMessagesForChat(UUID chatId) {
        return chatDao.getAllMessagesForChat(chatId);
    }

    public Message createMessageForChat(Message message, UUID chatId) {
        return chatDao.createNewMessageForChat(message, chatId);
    }

}
