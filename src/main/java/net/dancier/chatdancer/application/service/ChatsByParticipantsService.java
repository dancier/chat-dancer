package net.dancier.chatdancer.application.service;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantResponse;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantUseCase;
import net.dancier.chatdancer.application.port.out.ChatsByParticipantPort;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChatsByParticipantsService implements ChatsByParticipantUseCase {

    public static final Logger log = LoggerFactory.getLogger(ChatsByParticipantsService.class);

    private final ChatsByParticipantPort chatsByParticipantPort;

    @Override
    public List<ChatsByParticipantResponse> load(ChatsByParticipantQuery query) {
        log.info("Getting Participant for {}", query);
        return chatsByParticipantPort.getChatsByParticipant(query);
    }
}
