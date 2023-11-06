package net.dancier.chatdancer.application.port.in;

import lombok.Data;
import net.dancier.chatdancer.application.domain.model.Chat;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatsByParticipantResponse {

    Chat.ChatId chatId;

    Set<Chat.ParticipantId> participants;

    Message lastMessage;

    @Data
    public static class Message {
        String text;
        String authorId;
        UUID messageId;
        Set<Chat.ParticipantId> readBy;
        LocalDateTime localDateTime;
    }

}
