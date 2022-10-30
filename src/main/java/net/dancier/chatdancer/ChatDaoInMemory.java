package net.dancier.chatdancer;

import net.dancier.chatdancer.models.Chat;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class ChatDaoInMemory implements ChatDao {
 List<Chat> myChats = new LinkedList<>();
@Override
    public Chat createNewChatByParticipants(Chat chat) {
        UUID chatId = UUID.randomUUID();
        myChats.add(Chat.builder().chatId(chatId).chatType(chat.getChatType()).dancersIds(chat.getDancersIds()).build());
                return myChats.get(myChats.size() -1);

    }
    @Override
    public List<Chat> getAllChatsForUser (UUID id) {

    return myChats;

    }



}
