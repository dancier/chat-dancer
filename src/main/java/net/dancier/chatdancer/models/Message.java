package net.dancier.chatdancer.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class Message {
    private UUID id;
    private UUID chatId;
    private String text;
    private UUID authorId;
    private Timestamp creationTimestamp;
}
