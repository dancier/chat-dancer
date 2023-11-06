package net.dancier.chatdancer.application.port.out;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantResponse;

import java.util.List;

public interface ChatsByParticipantPort {

    List<ChatsByParticipantResponse> getChatsByParticipant(ChatsByParticipantQuery query);

}
