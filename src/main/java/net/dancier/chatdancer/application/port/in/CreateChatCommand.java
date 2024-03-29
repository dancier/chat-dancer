package net.dancier.chatdancer.application.port.in;

import net.dancier.chatdancer.application.domain.model.Chat;

import java.util.Set;

public record CreateChatCommand(Set<Chat.ParticipantId> participants) {}

