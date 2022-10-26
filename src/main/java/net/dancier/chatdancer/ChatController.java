package net.dancier.chatdancer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    ModelMapper modelMapper = new ModelMapper();
    @GetMapping
    public List<Chat> getAllChats() {
        return List.of( Chat.builder().chatId(UUID.randomUUID()).chatType(ChatType.GROUP).build());
    }

    @PostMapping
    public ResponseEntity<ChatCreatedResponseDto> createNewChat (@RequestBody ChatCreatedResponseDto chatCreatedResponseDto) {


         Chat createdChat = chatService.createNewChat(chatCreatedResponseDto);

         return ResponseEntity.ok(modelMapper.map(createdChat, ChatCreatedResponseDto.class));




    }


}
