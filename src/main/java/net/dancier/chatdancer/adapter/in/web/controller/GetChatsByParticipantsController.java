package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.adapter.in.web.GetChatResponseDto;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.service.ChatsByParticipantsService;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class GetChatsByParticipantsController {

    public static final Logger log = LoggerFactory.getLogger(GetChatsByParticipantsController.class);

    private final ChatsByParticipantsService chatsByParticipantsService;

    @GetMapping("/chats")
    public ResponseEntity<List<GetChatResponseDto>> getAllChats(@RequestParam String participantId) {
        log.info("Getting all Chats for " + participantId);
        ChatsByParticipantQuery query = new ChatsByParticipantQuery(new Chat.ParticipantId(participantId.toString()));
        List<Chat> chats = chatsByParticipantsService.load(query);
        List<GetChatResponseDto> responseList = chats.stream().map(c -> GetChatResponseDto.of(c)).collect(Collectors.toList());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
