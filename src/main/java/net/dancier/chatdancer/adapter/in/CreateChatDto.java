package net.dancier.chatdancer.adapter.in;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateChatDto {
    private List<String> dancerIds;

}
