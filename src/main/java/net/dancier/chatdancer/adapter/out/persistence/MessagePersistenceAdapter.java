package net.dancier.chatdancer.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.out.MessageAndChatIdDto;
import net.dancier.chatdancer.application.port.out.MessagePort;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class MessagePersistenceAdapter implements MessagePort {

    private final MessageJpaRepository messageJpaRepository;

    @Override
    public MessageAndChatIdDto loadMessage(Message.MessageId messageId) {
        return MessageMapper.jpaEntityToMessage(
                messageJpaRepository
                        .findById(messageId.getValue()).orElseThrow());
    }

    @Override
    public void update(MessageAndChatIdDto messageAndChatIdDto) {
        messageJpaRepository.save(MessageMapper.messageToJpaEntity(messageAndChatIdDto));
    }
}
