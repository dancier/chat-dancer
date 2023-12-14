package net.dancier.chatdancer.application.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SendEventWhenChatCreatedPort {
    public void send(SendChatCreatedDto sendChatCreatedDto) throws JsonProcessingException;

}
