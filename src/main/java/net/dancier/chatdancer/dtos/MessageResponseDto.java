package net.dancier.chatdancer.dtos;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class MessageResponseDto {
    private UUID id;
    private UUID chatId;
    private String text;
    private UUID authorId;
    private Timestamp createdAt;
}
