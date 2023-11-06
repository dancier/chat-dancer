package net.dancier.chatdancer.application.port.in;

import lombok.Value;

public record ChatsByParticipantQuery(
        ParticipantId participantId
){
    @Value
    public static class ParticipantId {
        private String value;
    }

}
