package net.dancier.chatdancer.application.domain.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Message {

    private final MessageId id;

    private final String text;

    private final AuthorId authorId;

    private final LocalDateTime createdAt;

    public static Message withId(MessageId messageId, String text, AuthorId authorId) {
        return new Message(messageId, text, authorId, LocalDateTime.now());
    }
    public static Message withoutId(
        String text,
        AuthorId authorId
    ) {
        return new Message(null,
                text,
                authorId,
                LocalDateTime.now());
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

