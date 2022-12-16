package net.dancier.chatdancer.dtos;

import lombok.Data;
import net.dancier.chatdancer.models.ChatType;

import java.util.List;
import java.util.UUID;

@Data
public class CreateNewChatRequestDto {
    private List<UUID> dancerIds;
    private ChatType type;
}
