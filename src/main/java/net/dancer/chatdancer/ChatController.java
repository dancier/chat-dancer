package net.dancer.chatdancer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @GetMapping("/chats")
    public String chat() {
        return "All availabble chat for the user";
    }

    @PostMapping("/chats")
    public String create_chat() {
        return "The newly created chat";
    }

    @GetMapping("/chats/{chat_id}")
    public String specific_chat() {
        return "chat based on id";
    }

    @GetMapping("/chats/{chat_id}/messages")
    public String chat_messages() {
        return "returns all messages for that chat";
    }

    @PostMapping("/chats/{chat_id}/messages")
    public String send_message() {
        return "creates a new message in a chat";
    }
}
