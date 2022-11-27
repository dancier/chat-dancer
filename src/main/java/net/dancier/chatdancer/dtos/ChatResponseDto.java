package net.dancier.chatdancer.dtos;

import lombok.Builder;
import lombok.Data;
import net.dancier.chatdancer.models.ChatType;
import net.dancier.chatdancer.models.Message;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ChatResponseDto {
    UUID chatId;
    List<UUID> dancerIds;
    Timestamp lastActivity;
    ChatType type;
    Message lastMessage;
    Timestamp creationTimestamp;

}
