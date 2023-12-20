package net.dancier.chatdancer.adapter.out.messaging;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class SendMessageCreatedEventDto
{
    UUID chatId;
    String authorId;
    UUID messageId;
    String text;
    OffsetDateTime createdAt;
}
