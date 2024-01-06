package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.adapter.in.web.MessageDto;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.GetMessagesByChatQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class GetMessagesController {

    private static final Logger log = LoggerFactory.getLogger(GetMessagesController.class);

    private final GetMessagesByChatQuery getMessagesByChatQuery;

    @GetMapping("/chats/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable UUID chatId) {
        log.info("Getting all Messages");
        List<Message> messages = getMessagesByChatQuery.byChatId(new Chat.ChatId(chatId));
        List<MessageDto> messageDtos = messages.stream().map(m -> MessageDto.of(m)).collect(Collectors.toList());
        return ResponseEntity.ok(messageDtos);
    }

}
