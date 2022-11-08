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
public class ChatResponseDTO {
    List<UUID> dancerIds;
    ChatType chatType;
    UUID chatID;
    List<Message> messages;
    Timestamp lastActivity;
    Message lastMessage;

}
