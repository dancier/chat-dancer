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
        chatJpaEntity.getMessages().stream().forEach(jpaMessageEntity -> chat.addMessage(Message.withId(
                        new Message.MessageId(jpaMessageEntity.getId()), jpaMessageEntity.getText(),
                        new Message.AuthorId(jpaMessageEntity.getAuthorId())))
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
                    JpaMessageEntity jme = new JpaMessageEntity();
                    if (m.getId()!=null) {
                        jme.setId(m.getId().getValue());
                    }
                    jme.setText(m.getText());
                    jme.setChatId(chat.getChatId().getId());
                    jme.setAuthorId(m.getAuthorId().getValue());
                    jme.setCreatedAt(LocalDateTime.now());
                    return jme;
                }
                )
                .collect(Collectors.toList()));
        chatJpaEntity.setCreatedAt(chat.getCreatedAt());
        log.info("Tranformed: " + chatJpaEntity);
        return chatJpaEntity;
    }
}
