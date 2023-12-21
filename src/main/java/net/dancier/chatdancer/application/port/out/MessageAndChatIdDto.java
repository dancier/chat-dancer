package net.dancier.chatdancer.application.port.out;

import lombok.Data;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;

@Data
public class MessageAndChatIdDto {

    Chat.ChatId chatId;

    Message message;

}
