package net.dancier.chatdancer.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.domain.model.Chat;
import net.dancier.chatdancer.application.domain.model.Message;
import net.dancier.chatdancer.application.port.in.SetReadFlagUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SetReadFlagOnMessageController {

    private final SetReadFlagUseCase setReadFlagUseCase;

    @PutMapping("/messages/{messageId}/read-by/{participantId}")
    public ResponseEntity set(@PathVariable UUID messageId, @PathVariable String participantId, @RequestBody SetReadFlagRequestDto setReadFlagRequestDto) {
        setReadFlagUseCase.setReadFlag(new Message.MessageId(messageId), new Chat.ParticipantId(participantId), setReadFlagRequestDto.getRead());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
