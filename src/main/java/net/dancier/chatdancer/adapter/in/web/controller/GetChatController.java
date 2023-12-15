package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.adapter.in.web.GetChatResponseDto;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.port.in.GetChatUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GetChatController {

    private final static Logger log = LoggerFactory.getLogger(GetChatController.class);
    public static final String GET_ENDPOINT = "/chats/{id}";

    private final GetChatUseCase getChatUseCase;

    @GetMapping(value = GET_ENDPOINT)
    public ResponseEntity<GetChatResponseDto> getChat(@PathVariable UUID id) {
        Chat chat = getChatUseCase.get(new Chat.ChatId(id));
        GetChatResponseDto getChatResponseDto = GetChatResponseDto.of(chat);
        return new ResponseEntity(getChatResponseDto, HttpStatus.OK);
    }

}
