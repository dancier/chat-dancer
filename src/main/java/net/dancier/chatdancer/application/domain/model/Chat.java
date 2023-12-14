package net.dancier.chatdancer.application.domain.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;


@ToString
public class Chat {

    private static final Logger log = LoggerFactory.getLogger(Chat.class);

    @Getter
    private ChatId chatId;

    private final Set<ParticipantId> chatParticipants = new HashSet<>();

    private List<Message> messages = new LinkedList<>();

    @Getter
    private LocalDateTime createdAt;

    private Chat(ChatId chatId, LocalDateTime createdAt){
        this.chatId = chatId;
        this.createdAt = createdAt;
    }

    public static Chat withId(ChatId chatId, LocalDateTime createdAt) {
        return new Chat(chatId, createdAt);
    }

    public static Chat withoutId(LocalDateTime createdAt) {
        return new Chat(null, createdAt);
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(this.messages);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public Set<ParticipantId> getChatParticipants() {
        return Collections.unmodifiableSet(this.chatParticipants);
    }
    public void addParticipant(ParticipantId participantId) {
        this.chatParticipants.add(participantId);
    }

    @Value
    public static class ChatId {
        private UUID id;
    }

    @Value
    public static class ParticipantId {
        private String id;
    }
}
