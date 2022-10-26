package net.dancier.chatdancer;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class ChatCreatedResponseDto {
    List<UUID> participantsIds;
    ChatType chatType;
    UUID chatID;

}
