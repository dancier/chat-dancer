package net.dancier.chatdancer.application.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SendChatCreatedEventPort {
    public void send(SendChatCreatedEventDto sendChatCreatedEventDto) throws JsonProcessingException;

}
