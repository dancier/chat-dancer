package net.dancier.chatdancer;

import net.dancier.chatdancer.models.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatDao {

    Chat createNewChatByParticipants(Chat chat);

    List<Chat> getAllChatsForUser (UUID id);
}
