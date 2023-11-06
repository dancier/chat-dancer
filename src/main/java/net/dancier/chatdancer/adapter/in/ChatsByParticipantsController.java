package net.dancier.chatdancer.adapter.in;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantQuery;
import net.dancier.chatdancer.application.port.in.ChatsByParticipantResponse;
import net.dancier.chatdancer.application.service.ChatsByParticipantsService;
import net.dancier.chatdancer.dtos.ChatsResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ChatsByParticipantsController {

    public static final Logger log = LoggerFactory.getLogger(ChatsByParticipantsController.class);

    private final ChatsByParticipantsService chatsByParticipantsService;

    @GetMapping("/h/chats")
    public ResponseEntity<List<ChatsByParticipantResponse>> getAllChats(@RequestParam UUID dancerId) {
        log.info("Getting all Chats for " + dancerId);
        ChatsByParticipantQuery query = new ChatsByParticipantQuery(new ChatsByParticipantQuery.ParticipantId(dancerId.toString()));
        List<ChatsByParticipantResponse> responseList = chatsByParticipantsService.load(query);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
