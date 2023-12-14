package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.adapter.in.web.MessageDto;
import net.dancier.chatdancer.adapter.in.web.PostChatMessageRequestDto;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.MessagesByChatUseCase;
import net.dancier.chatdancer.application.port.in.PostChatMessageCommand;
import net.dancier.chatdancer.application.port.in.PostChatMessageUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ChatMessageController {

    public static final Logger log = LoggerFactory.getLogger(ChatMessageController.class);

    private final PostChatMessageUseCase postChatMessageUseCase;

    private final MessagesByChatUseCase messagesByChatUseCase;

    @GetMapping("/h/chats/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable UUID chatId) {
        log.info("Getting all Messages");
        List<Message> messages = messagesByChatUseCase.byChatId(new Chat.ChatId(chatId));
        List<MessageDto> messageDtos = messages.stream().map(m -> MessageDto.of(m)).collect(Collectors.toList());
        return ResponseEntity.ok(messageDtos);
    }

    @PostMapping("/h/chats/{chatId}/messages")
    public ResponseEntity postChatMessage(@PathVariable UUID chatId, @Validated @RequestBody PostChatMessageRequestDto postChatMessageRequestDto) {
        PostChatMessageCommand postChatMessageCommand = new PostChatMessageCommand(
                postChatMessageRequestDto.getText(),
                new Message.AuthorId(postChatMessageRequestDto.getAuthorId()),
                new Chat.ChatId((chatId)));
        postChatMessageUseCase.post(postChatMessageCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
