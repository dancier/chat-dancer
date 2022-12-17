package net.dancier.chatdancer.controllers;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.dtos.*;
import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.models.Message;
import net.dancier.chatdancer.services.ChatService;
import net.dancier.chatdancer.utils.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    public static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatsResponseDto> getAllChats(@RequestParam UUID dancerId) {

        List<Chat> allChatsForDancer = chatService.getAllChatsForDancer(dancerId);
        List<ChatResponseDto> chatResponseDtoList = allChatsForDancer.stream().map(this::convertChatToDto).toList();
        ChatsResponseDto chatsResponseDto = ChatsResponseDto.builder().chats(chatResponseDtoList).build();

        return new ResponseEntity<>(chatsResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponseDto> getChatById(@PathVariable UUID chatId) {

        Chat requestedChat = chatService.getChatById(chatId);

        return new ResponseEntity<>(convertChatToDto(requestedChat), HttpStatus.OK);
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<MessagesResponseDto> getAllMessagesForChat(@PathVariable UUID chatId) {

        List<MessageResponseDto> messageResponseDtoList =
                chatService.getAllMessagesForChat(chatId).stream().map(this::convertMessageToDto).toList();
        MessagesResponseDto messagesResponseDto = MessagesResponseDto.builder().messages(messageResponseDtoList).build();
        return new ResponseEntity<>(messagesResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChatResponseDto> createNewChat(@Validated @RequestBody CreateNewChatRequestDto createNewChatRequestDTO) {
        if (createNewChatRequestDTO.getType() == null) {
            throw new BadRequestException("chat type must exist");
        }

        Chat chat = convertDtoToChat(createNewChatRequestDTO);

        Chat createdChat = chatService.createNewChat(chat);

        ChatResponseDto chatResponseDTO = convertChatToDto(createdChat);

        log.info("Created Chat: " + chatResponseDTO);

        return new ResponseEntity<>(chatResponseDTO, HttpStatus.CREATED);


    }

    @PostMapping("/{chatId}/messages")
    ResponseEntity<Message> createNewMessage(@PathVariable UUID chatId, @Validated @RequestBody CreateMessageDto createMessageDto) {

        Message newMessage = Message.builder().text(createMessageDto.getText()).authorId(createMessageDto.getAuthorId()).build();

        return new ResponseEntity<>(chatService.createMessageForChat(newMessage, chatId), HttpStatus.CREATED);

    }

    private ChatResponseDto convertChatToDto(Chat chat) {

        return ChatResponseDto.builder()
                .chatId(chat.getChatId())
                .dancerIds(chat.getDancersIds())
                .lastActivity(chat.getLastActivity())
                .type(chat.getType())
                .lastMessage(chat.getLastMessage())
                .creationTimestamp(chat.getCreationTimestamp())
                .build();

    }

    private MessageResponseDto convertMessageToDto(Message message) {

        return MessageResponseDto.builder()
                .chatId(message.getChatId())
                .creationTimestamp(message.getCreationTimestamp())
                .text(message.getText())
                .id(message.getId())
                .authorId(message.getAuthorId())
                .build();

    }

    private Chat convertDtoToChat(CreateNewChatRequestDto createNewChatRequestDTO) {

        return Chat.builder()
                .type(createNewChatRequestDTO.getType())
                .dancersIds(createNewChatRequestDTO.getDancerIds())
                .build();

    }
}
