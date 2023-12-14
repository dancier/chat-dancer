package net.dancier.chatdancer.application.port.out;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

import java.util.Set;
import java.util.UUID;

@Data
public class SendChatCreatedDto {

    private UUID chatId;

    private Set<Chat.ParticipantId> participantIds;

}
