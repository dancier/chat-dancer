package net.dancier.chatdancer.adapter.in.web;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostChatMessageRequestDto {
    @NotNull(message = "The id of the author of the message must be provided")
    private String authorId;
    private String text;
}
