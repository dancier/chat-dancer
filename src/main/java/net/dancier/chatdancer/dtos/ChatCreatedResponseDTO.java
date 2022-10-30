package net.dancier.chatdancer.dtos;

import lombok.Builder;
import lombok.Data;
import net.dancier.chatdancer.models.ChatType;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class ChatCreatedResponseDTO {
    List<UUID> dancerIds;
    ChatType chatType;
    UUID chatID;

}
