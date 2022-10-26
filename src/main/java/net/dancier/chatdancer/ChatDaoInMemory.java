package net.dancier.chatdancer;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class ChatDaoInMemory implements ChatDao {
 List<Chat> myChats = new LinkedList<>();
@Override
    public Chat createNewChatByParticipants(ChatCreatedResponseDto chatCreatedResponseDto) {
        UUID chatId = UUID.randomUUID();
        myChats.add(Chat.builder().chatId(chatId).chatType(chatCreatedResponseDto.getChatType()).userIds(chatCreatedResponseDto.getParticipantsIds()).build());
                return myChats.get(myChats.size() -1);

    }



}
