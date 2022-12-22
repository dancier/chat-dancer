package net.dancier.chatdancer.dao;

import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.models.Message;

import java.util.List;
import java.util.UUID;

public interface ChatDao {

    Chat createNewChatByParticipants(Chat chat);

    List<Chat> getAllChatsForDancer(UUID id);

    Chat getChatById(UUID chatId);

    List<Message> getAllMessagesForChat(UUID chatId);

    Message createNewMessageForChat(Message message, UUID chatId);
}
