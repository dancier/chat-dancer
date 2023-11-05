package net.dancier.chatdancer.application.domain.model;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
public class Chat {
    private UUID chatId;
    private Set<ParticipantId> chatParticipants;
    List<Message> messages;
    LocalDateTime createdAt;

    @Value
    public static class ChatId {
        private UUID value;
    }

    @Value
    public static class ParticipantId {
        private String value;
    }
}
