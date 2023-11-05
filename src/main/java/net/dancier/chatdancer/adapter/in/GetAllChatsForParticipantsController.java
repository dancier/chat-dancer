package net.dancier.chatdancer.adapter.in;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.in.GetAllChatsForParticipantQuery;
import net.dancier.chatdancer.application.service.GetAllChatsForParticipantsService;
import net.dancier.chatdancer.dtos.ChatsResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class GetAllChatsForParticipantsController {

    public static final Logger log = LoggerFactory.getLogger(GetAllChatsForParticipantsController.class);

    private final GetAllChatsForParticipantsService getAllChatsForParticipantsService;

    @GetMapping("/h/chats")
    public ResponseEntity<ChatsResponseDto> getAllChats(@RequestParam UUID dancerId) {
        log.info("Getting all Chats for " + dancerId);
        GetAllChatsForParticipantQuery query = new GetAllChatsForParticipantQuery(new GetAllChatsForParticipantQuery.ParticipantId(dancerId.toString()));
        getAllChatsForParticipantsService.getAllChats(query);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
