package net.dancier.chatdancer.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class CreateMessageDto {
    @NotNull(message = "The id of the author of the message must be provided")
    private UUID authorId;
    private String text;
}
