package net.dancier.chatdancer.adapter.out.persistence;

import lombok.AllArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.out.LoadChatPort;
import net.dancier.chatdancer.application.port.out.UpdateChatPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChatPersistenceAdapter implements LoadChatPort, UpdateChatPort {

    public static final Logger log = LoggerFactory.getLogger(ChatPersistenceAdapter.class);

    private final ChatJpaRepository chatJpaRepository;

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
}
