package net.dancier.chatdancer.adapter.out.persistence;

import lombok.AllArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;
import net.dancier.chatdancer.application.port.out.ChatsByParticipantPort;
import net.dancier.chatdancer.application.port.out.GetChatPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChatPersistenceAdapter implements GetChatPort, UpdateChatPort, ChatsByParticipantPort {

    public static final Logger log = LoggerFactory.getLogger(ChatPersistenceAdapter.class);

    private final ChatJpaRepository chatJpaRepository;

    private final ChatMapper chatMapper;

    @Override
    public Chat get(Chat.ChatId chatId) {
        ChatJpaEntity jpaChatEntity = chatJpaRepository.findById(chatId.getId()).orElseThrow();
        log.info("got: " + jpaChatEntity);
        return chatMapper.fromJpaChatEntityToChat(jpaChatEntity);
    }

    @Override
    public Chat.ChatId updateChat(Chat chat) {
        log.info("updating: " + chat);
        ChatJpaEntity chatJpaEntity = chatMapper.fromChatToJpaChatEntity(chat);
        chatJpaRepository.save(chatJpaEntity);
        log.info("done: " + chat);
        return new Chat.ChatId(chatJpaEntity.getId());
    }


    @Override
    public List<Chat> getChatsByParticipant(ChatsByParticipantQuery query) {
        log.info("Getting chat in persistence layer for participant: {}", query);
        List<ChatJpaEntity> jpaChatEntities = chatJpaRepository.findByParticipant(query.participantId().getId());
        log.info("found this: " + jpaChatEntities);
        return jpaChatEntities.stream().map(chatMapper::fromJpaChatEntityToChat).collect(Collectors.toList());
    }
}
