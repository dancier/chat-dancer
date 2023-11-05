package net.dancier.chatdancer.application.port.in;

import lombok.Value;
import net.dancier.chatdancer.application.domain.model.Chat;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CreateChatCommand(
        Set<Chat.ParticipantId> participants
) {

}

