package net.dancier.chatdancer.adapter.in.web;

import lombok.Data;

import java.util.List;

@Data
public class PostChatRequestDto {
    private List<String> participantIds;
}
