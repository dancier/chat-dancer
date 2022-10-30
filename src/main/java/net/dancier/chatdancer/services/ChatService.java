package net.dancier.chatdancer.services;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.ChatDao;
import net.dancier.chatdancer.ChatDaoInMemory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

 private final ChatDaoInMemory chatDaoInMemory;

 public Chat createNewChat(Chat chat) {

           return chatDaoInMemory.createNewChatByParticipants(chat);
 };


public List<Chat> getAllChatsForUser (UUID id) {

    return chatDaoInMemory.getAllChatsForUser(id);
}

}
