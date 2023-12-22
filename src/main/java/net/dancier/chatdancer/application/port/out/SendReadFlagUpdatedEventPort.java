package net.dancier.chatdancer.application.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SendReadFlagUpdatedEventPort {

    void send(SendReadFlagUpdatedEventDto sendReadFlagUpdatedEventDto) throws JsonProcessingException;

}
