package net.dancier.chatdancer.application.port.in;

import lombok.Value;

import java.util.List;
import java.util.UUID;

public record CreateChatCommand(
        List<ParticipantId> participants
) {

    @Value
    public static class ParticipantId {
        private String value;
    }

}

