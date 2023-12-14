package net.dancier.chatdancer.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class GetChatResponseDto {
    public static GetChatResponseDto of(Chat chat) {
        Optional<Message> optionalLastMessage = chat.getMessages().stream().reduce((first, second) -> second);
        return GetChatResponseDto.builder()
                .chatId(chat.getChatId().getId())
                .participantIds(
                        chat.getChatParticipants().stream()
                                .map(Chat.ParticipantId::getId).collect(Collectors.toSet())
                ).createdAt(OffsetDateTime.of(chat.getCreatedAt(), ZoneOffset.UTC))
                .lastMessage(
                        MessageDto.of(
                                chat.getMessages().stream().reduce(
                                        (first, second) -> second
                                ).orElse(null)
                        )
                ).lastActivity(
                        optionalLastMessage
                                .map(m -> OffsetDateTime.of(m.getCreatedAt(), ZoneOffset.UTC))
                                .orElse(null)
                )
                .build();
    }

    UUID chatId;
    Set<String> participantIds;
    OffsetDateTime lastActivity;
    MessageDto lastMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    OffsetDateTime createdAt;
}
