package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.adapter.in.web.CreatedChatDto;
import net.dancier.chatdancer.adapter.in.web.PostChatRequestDto;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.CreateChatCommand;
import net.dancier.chatdancer.application.port.in.CreateChatUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.text.MessageFormat;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PostChatController {

    public final Logger log = LoggerFactory.getLogger(PostChatController.class);
    public final static String CREATE_CHAT_ENDPOINT = "/chats";
    public final static String GET_CHAT_URI_PATTERN = "/chats/{0}";

    private final CreateChatUseCase createChatUseCase;

    @PostMapping(CREATE_CHAT_ENDPOINT)
    ResponseEntity<CreatedChatDto> createChat(@Validated @RequestBody PostChatRequestDto postChatDto) {
        CreateChatCommand createChatCommand = new CreateChatCommand(
            postChatDto.getParticipantIds().stream().map(str -> new Chat.ParticipantId(str)).collect(Collectors.toSet())
        );
        Chat.ChatId chatId = createChatUseCase.createChat(createChatCommand);
        URI uri = URI.create(
                MessageFormat
                        .format(GET_CHAT_URI_PATTERN, chatId.getId())
        );
        CreatedChatDto createdChatDto = new CreatedChatDto();
        createdChatDto.setId(chatId.getId());
        return ResponseEntity.created(uri).body(createdChatDto);
    }

}
