package net.dancier.chatdancer.application.port.in;

import lombok.Value;

public record GetAllChatsForParticipantQuery (
        ParticipantId participantId
){
    @Value
    public static class ParticipantId {
        private String value;
    }

}
