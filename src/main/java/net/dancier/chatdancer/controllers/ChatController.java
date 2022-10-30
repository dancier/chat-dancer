package net.dancier.chatdancer.controllers;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.dtos.CreateNewChatRequestDTO;
import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.dtos.ChatCreatedResponseDTO;
import net.dancier.chatdancer.services.ChatService;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    public static Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatService chatService;

    @GetMapping
    public List<Chat> getAllChats(@RequestParam UUID userId) {
        return chatService.getAllChatsForUser(userId);
    }

    @PostMapping
    public ResponseEntity<ChatCreatedResponseDTO> createNewChat(@RequestBody CreateNewChatRequestDTO createNewChatRequestDTO) {
        Chat chat = convertDtoToChat(createNewChatRequestDTO);

        Chat createdChat = chatService.createNewChat(chat);

        ChatCreatedResponseDTO chatCreatedResponseDTO = convertChatToDto(createdChat);

        log.info("Creating Chat: " + chatCreatedResponseDTO);

        return new ResponseEntity<>(chatCreatedResponseDTO, HttpStatus.CREATED);





    }
    private ChatCreatedResponseDTO convertChatToDto (Chat chat){

        return ChatCreatedResponseDTO.builder()
                .chatID(chat.getChatId())
                .chatType(chat.getChatType())
                .dancerIds(chat.getDancersIds())
                .build();

    }

    private Chat convertDtoToChat (CreateNewChatRequestDTO createNewChatRequestDTO){

        return Chat.builder()
                .chatId(UUID.randomUUID())
                .chatType(createNewChatRequestDTO.getChatType())
                .dancersIds(createNewChatRequestDTO.getDancerIds())
                .build();

    }
}
