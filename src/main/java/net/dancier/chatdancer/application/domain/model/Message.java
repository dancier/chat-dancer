package net.dancier.chatdancer.application.domain.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Message {

    private final MessageId id;

    private final String text;

    private final AuthorId authorId;

    private final LocalDateTime createdAt;

    private final Set<Chat.ParticipantId> readBy = new HashSet<>();

    public static Message withId(MessageId messageId, String text, AuthorId authorId) {
        return new Message(messageId, text, authorId, LocalDateTime.now());
    }
    public static Message withoutId(
        String text,
        AuthorId authorId
    ) {
        return new Message(new MessageId(UUID.randomUUID()),
                text,
                authorId,
                LocalDateTime.now());
    }

    public void addReadBy(Chat.ParticipantId participantId) {
        this.readBy.add(participantId);
    }

    public void removeReadBy(Chat.ParticipantId participantId) {
        this.readBy.remove(participantId);
    }

    @Value
    public static class MessageId {
        private UUID value;
    }
    @Value
    public static class AuthorId {
        private String value;
    }
}

