package net.dancier.chatdancer.application.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

public interface SendMessageCreatedEventPort {

    void send(Chat chat, Message message) throws JsonProcessingException;

}
