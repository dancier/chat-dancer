package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;

public record ChatsByParticipantQuery(Chat.ParticipantId participantId) {
}
