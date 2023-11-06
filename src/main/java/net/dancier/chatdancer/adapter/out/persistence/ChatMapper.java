package net.dancier.chatdancer.adapter.out.persistence;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChatMapper {

    ChatsByParticipantResponse mapFromJpaChatEntity(JpaChatEntity jpaChatEntity) {
        ChatsByParticipantResponse chatsByParticipantResponse = new ChatsByParticipantResponse();
        chatsByParticipantResponse.setChatId(new Chat.ChatId(jpaChatEntity.getId()));
        chatsByParticipantResponse.setParticipants(
                jpaChatEntity
                        .getParticipants()
                        .stream()
                        .map(id -> new Chat.ParticipantId(id)).collect(Collectors.toSet()));
        return chatsByParticipantResponse;
    }

}
