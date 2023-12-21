package net.dancier.chatdancer.adapter.out.persistence;

import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.out.MessageAndChatIdDto;

import java.util.stream.Collectors;

public class MessageMapper {

    public static MessageAndChatIdDto jpaEntityToMessage(MessageJpaEntity messageJpaEntity) {
        Message message =
                Message.withId(
                        new Message.MessageId(messageJpaEntity.getId()),
                        messageJpaEntity.getText(),
                        new Message.AuthorId(messageJpaEntity.getAuthorId())
                );
        messageJpaEntity.getReadyBy().forEach(rbs -> message.addReadBy(new Chat.ParticipantId(rbs)));
        MessageAndChatIdDto messageAndChatIdDto = new MessageAndChatIdDto();
        messageAndChatIdDto.setMessage(message);
        messageAndChatIdDto.setChatId(new Chat.ChatId(messageJpaEntity.getChatId()));
        return messageAndChatIdDto;
    }

    public static MessageJpaEntity messageToJpaEntity(MessageAndChatIdDto messageAndChatIdDto) {
        Message message = messageAndChatIdDto.getMessage();

        MessageJpaEntity messageJpaEntity = new MessageJpaEntity();
        messageJpaEntity.setId(message.getId().getValue());
        messageJpaEntity.setText(message.getText());
        messageJpaEntity.setChatId(messageAndChatIdDto.getChatId().getId());
        messageJpaEntity.setAuthorId(message.getAuthorId().getValue());
        messageJpaEntity.setReadyBy(message.getReadBy().stream().map(p-> p.getId()).collect(Collectors.toSet()));
        messageJpaEntity.setCreatedAt(message.getCreatedAt());
        return messageJpaEntity;
    }
}
