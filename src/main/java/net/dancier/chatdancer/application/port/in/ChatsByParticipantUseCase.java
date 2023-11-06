package net.dancier.chatdancer.application.port.in;

import java.util.List;

public interface ChatsByParticipantUseCase {

    List<ChatsByParticipantResponse> load(ChatsByParticipantQuery query);

}
