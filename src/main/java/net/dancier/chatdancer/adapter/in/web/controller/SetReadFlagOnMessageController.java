package net.dancier.chatdancer.adapter.in.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SetReadFlagOnMessageController {

    @PutMapping("/messages/{messageId}/read-by/{participantId}")
    public ResponseEntity set(@PathVariable UUID messageId, @PathVariable String participantId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
