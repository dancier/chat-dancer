package net.dancier.chatdancer.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.dancier.chatdancer.models.ChatType;

import java.util.List;
import java.util.UUID;

@Data
public class CreateNewChatRequestDto {
    @NotNull(message = "Id of the dancers participating in a chat must be included")
    private List<UUID> dancerIds;
    @NotNull(message = "chat type must be provided. A chat type can be either DIRECT or GROUP")
    private ChatType type;
}
