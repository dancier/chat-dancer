package net.dancier.chatdancer.adapter.in.web;

import lombok.Builder;
import lombok.Data;
import net.dancier.chatdancer.application.domain.model.Message;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Data
@Builder
public class MessageDto {
    public static MessageDto of(Message message) {
        if (message!=null) {
            return MessageDto.builder()
                    .id(message.getId().getValue().toString())
                    .text(message.getText())
                    .authorId(message.getAuthorId().getValue().toString())
                    .createdAt(OffsetDateTime.of(message.getCreatedAt(), ZoneOffset.UTC))
                    .build();
        } else {
            return null;
        }
    };

    private String text;
    private String id;
    private String authorId;
    private Set<String> readByParticipants;
    private OffsetDateTime createdAt;

}
