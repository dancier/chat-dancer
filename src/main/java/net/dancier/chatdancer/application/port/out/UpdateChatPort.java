package net.dancier.chatdancer.application.port.out;

import net.dancier.chatdancer.application.domain.model.Chat;

public interface UpdateChatPort {

    void updateChat(Chat chat);

}
