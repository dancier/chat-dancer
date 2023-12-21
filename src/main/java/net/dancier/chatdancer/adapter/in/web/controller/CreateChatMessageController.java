package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.adapter.in.web.PostChatMessageRequestDto;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.CreateChatMessageCommand;
import net.dancier.chatdancer.application.port.in.CreateChatMessageUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CreateChatMessageController {

    public static final Logger log = LoggerFactory.getLogger(CreateChatMessageController.class);

    private final CreateChatMessageUseCase createChatMessageUseCase;

    @PostMapping("/chats/{chatId}/messages")
    public ResponseEntity<CreatedChatMessageDto> postChatMessage(@PathVariable UUID chatId, @Validated @RequestBody PostChatMessageRequestDto postChatMessageRequestDto) {
        CreateChatMessageCommand createChatMessageCommand = new CreateChatMessageCommand(
                postChatMessageRequestDto.getText(),
                new Message.AuthorId(postChatMessageRequestDto.getAuthorId()),
                new Chat.ChatId((chatId)));

        Message.MessageId messageId = createChatMessageUseCase.create(createChatMessageCommand);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/chats/" + chatId.toString() + "/messages/" + messageId.getValue().toString())
                .build()
                .toUri();

        CreatedChatMessageDto createdChatMessageDto = new CreatedChatMessageDto();
        createdChatMessageDto.setId(messageId.getValue().toString());
        
        return ResponseEntity.created(uri).body(createdChatMessageDto);
    }
}
