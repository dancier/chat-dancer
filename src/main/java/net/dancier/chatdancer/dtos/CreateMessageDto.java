package net.dancier.chatdancer.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateMessageDto {
    @NotNull(message = "The id of the author of the message must be provided")
    private UUID authorId;
    private String text;
}
