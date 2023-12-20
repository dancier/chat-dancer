package net.dancier.chatdancer.application.port.out;

import lombok.Data;
import net.dancier.chatdancer.application.domain.model.Chat;

import java.util.Set;
import java.util.UUID;

@Data
public class SendChatCreatedEventDto {

    private UUID chatId;

    private Set<Chat.ParticipantId> participantIds;

}
