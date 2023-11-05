package net.dancier.chatdancer.application.service;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.GetAllChatsForParticipantQuery;
import net.dancier.chatdancer.application.port.in.GetAllChatsForParticipantUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllChatsForParticipantsService implements GetAllChatsForParticipantUseCase {

    @Override
    public List<Chat> getAllChats(GetAllChatsForParticipantQuery query) {
        return List.of();
    }
}
