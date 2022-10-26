package net.dancier.chatdancer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

 private final ChatDao chatDao;
 private final ChatDaoInMemory chatDaoInMemory;

 public Chat createNewChat(ChatCreatedResponseDto chatCreatedResponseDto) {

           return chatDaoInMemory.createNewChatByParticipants(chatCreatedResponseDto);
 };
}
