package net.dancier.chatdancer.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateMessageDTO {
    private UUID authorId;
    private String text;
}
