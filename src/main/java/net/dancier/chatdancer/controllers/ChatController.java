package net.dancier.chatdancer.controllers;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.dtos.ChatResponseDTO;
import net.dancier.chatdancer.dtos.CreateMessageDTO;
import net.dancier.chatdancer.dtos.CreateNewChatRequestDTO;
import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.models.Message;
import net.dancier.chatdancer.services.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Chat>> getAllChats(@RequestParam UUID dancerId) {
        return new ResponseEntity<>(chatService.getAllChatsForUser(dancerId), HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponseDTO> getChatById(@PathVariable UUID chatId) {

        Chat requestedChat = chatService.getChatById(chatId);

        return new ResponseEntity<>(convertChatToDto(requestedChat), HttpStatus.OK);
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesForChat(@PathVariable UUID chatId) {

        return new ResponseEntity<>(chatService.getAllMessagesForChat(chatId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ChatResponseDTO> createNewChat(@RequestBody CreateNewChatRequestDTO createNewChatRequestDTO) {
        Chat chat = convertDtoToChat(createNewChatRequestDTO);

        Chat createdChat = chatService.createNewChat(chat);

        ChatResponseDTO chatResponseDTO = convertChatToDto(createdChat);

        log.info("Creating Chat: " + chatResponseDTO);

        return new ResponseEntity<>(chatResponseDTO, HttpStatus.CREATED);


    }

    @PostMapping("/{chatId}/messages")
    ResponseEntity<Message> createNewMessage(@PathVariable UUID chatId, @RequestBody CreateMessageDTO createMessageDTO) {

        Message newMessage = Message.builder().text(createMessageDTO.getText()).authorId(createMessageDTO.getAuthorId()).build();

        return new ResponseEntity<>(chatService.createMessageForChat(newMessage, chatId), HttpStatus.OK);

    }

    private ChatResponseDTO convertChatToDto(Chat chat) {

        return ChatResponseDTO.builder()
                .chatID(chat.getChatId())
                .chatType(chat.getChatType())
                .dancerIds(chat.getDancersIds())
                .messages(chat.getMessages())
                .lastMessage(chat.getLastMessage())
                .build();

    }

    private Chat convertDtoToChat(CreateNewChatRequestDTO createNewChatRequestDTO) {

        return Chat.builder()
                .chatType(createNewChatRequestDTO.getType())
                .dancersIds(createNewChatRequestDTO.getDancerIds())
                .build();

    }
}
