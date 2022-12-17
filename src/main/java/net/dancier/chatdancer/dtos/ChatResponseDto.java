package net.dancier.chatdancer.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import net.dancier.chatdancer.models.ChatType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ChatResponseDto {
    UUID chatId;
    List<UUID> dancerIds;
    LocalDateTime lastActivity;
    ChatType type;
    MessageResponseDto lastMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    LocalDateTime createdAt;

}