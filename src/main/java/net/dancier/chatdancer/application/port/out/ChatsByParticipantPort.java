package net.dancier.chatdancer.application.port.out;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;

import java.util.List;

public interface ChatsByParticipantPort {

    List<Chat> getChatsByParticipant(ChatsByParticipantQuery query);

}
