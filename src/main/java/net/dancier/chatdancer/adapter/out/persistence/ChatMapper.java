package net.dancier.chatdancer.adapter.out.persistence;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class ChatMapper {

    private final static Logger log = LoggerFactory.getLogger(ChatMapper.class);

    ChatsByParticipantResponse mapToChatsByParticipantsResponse(ChatJpaEntity chatJpaEntity) {
        ChatsByParticipantResponse chatsByParticipantResponse = new ChatsByParticipantResponse();
        chatsByParticipantResponse.setChatId(new Chat.ChatId(chatJpaEntity.getId()));
        chatsByParticipantResponse.setParticipants(
                chatJpaEntity
                        .getParticipants()
                        .stream()
                        .map(id -> new Chat.ParticipantId(id)).collect(Collectors.toSet()));
        return chatsByParticipantResponse;
    }

    Chat fromJpaChatEntityToChat(ChatJpaEntity chatJpaEntity) {
        Chat chat = Chat.withId(new Chat.ChatId(chatJpaEntity.getId()), chatJpaEntity.getCreatedAt());
        chatJpaEntity.getParticipants().stream().forEach(s -> chat.addParticipant(new Chat.ParticipantId(s)));
        chatJpaEntity.getMessages().stream().forEach(messageJpaEntity -> {
            Message message = Message.withId(
                    new Message.MessageId(messageJpaEntity.getId()),
                    messageJpaEntity.getText(),
                    new Message.AuthorId(messageJpaEntity.getAuthorId()),
                    messageJpaEntity.getCreatedAt()
                    );
            messageJpaEntity.getReadyBy().forEach(participantString -> message.getReadBy().add(new Chat.ParticipantId(participantString)));
            chat.addMessage(message);
        }
        );
        return chat;
    }

    ChatJpaEntity fromChatToJpaChatEntity(Chat chat) {
        ChatJpaEntity chatJpaEntity = new ChatJpaEntity();
        if (chat.getChatId() != null) {
            chatJpaEntity.setId(chat.getChatId().getId());
        }
        chatJpaEntity.setParticipants(chat.getChatParticipants().stream().map(Chat.ParticipantId::getId).collect(Collectors.toSet()));
        chatJpaEntity.setMessages(chat.getMessages().stream()
                .map(m -> {
                    MessageJpaEntity jme = new MessageJpaEntity();
                    if (m.getId()!=null) {
                        jme.setId(m.getId().getValue());
                    }
                    jme.setText(m.getText());
                    jme.setChatId(chat.getChatId().getId());
                    jme.setAuthorId(m.getAuthorId().getValue());
                    jme.setCreatedAt(LocalDateTime.now());
                    jme.setReadyBy(m.getReadBy().stream().map(p -> p.getId()).collect(Collectors.toSet()));
                    return jme;
                }
                )
                .collect(Collectors.toList()));
        chatJpaEntity.setCreatedAt(chat.getCreatedAt());
        return chatJpaEntity;
    }
}
