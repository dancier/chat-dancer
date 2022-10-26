package net.dancier.chatdancer;

public interface ChatDao {

    Chat createNewChatByParticipants(ChatCreatedResponseDto chatCreatedResponseDto);
}
