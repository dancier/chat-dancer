package net.dancier.chatdancer.adapter.out.persistence;

import lombok.AllArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantResponse;
import net.dancier.chatdancer.application.port.out.ChatsByParticipantPort;
import net.dancier.chatdancer.application.port.out.LoadChatPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChatPersistenceAdapter implements LoadChatPort, UpdateChatPort, ChatsByParticipantPort {

    public static final Logger log = LoggerFactory.getLogger(ChatPersistenceAdapter.class);

    private final ChatJpaRepository chatJpaRepository;

    private final ChatMapper chatMapper;

    @Override
    public Chat loadChat(Chat.ChatId chatId) {
        return null;
    }

    @Override
    public void updateChat(Chat chat) {

        JpaChatEntity jpaChatEntity = new JpaChatEntity();
        jpaChatEntity.setId(chat.getChatId());
        jpaChatEntity.setParticipants(chat.getChatParticipants().stream().map(p -> p.getValue()).collect(Collectors.toSet()));
        chatJpaRepository.save(jpaChatEntity);

    }

    @Override
    public List<ChatsByParticipantResponse> getChatsByParticipant(ChatsByParticipantQuery query) {
        log.info("Getting chat in persistence layer for participant: {}", query);
        List<JpaChatEntity> jpaChatEntities = chatJpaRepository.findByParticipant(query.participantId().getValue());
        log.info("found this: " + jpaChatEntities);
        return jpaChatEntities.stream().map(chatMapper::mapFromJpaChatEntity).collect(Collectors.toList());
    }
}
