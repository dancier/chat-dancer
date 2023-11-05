package net.dancier.chatdancer.application.domain.model;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Chat {
    private UUID chatId;
    private Set<UUID> chatParticipants;
    List<Message> messages;
    LocalDateTime createdAt;

    @Value
    public static class ChatId {
        private UUID value;
    }
}
