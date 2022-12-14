package net.dancier.chatdancer.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatsResponseDto {
    List<ChatResponseDto> chats;
}