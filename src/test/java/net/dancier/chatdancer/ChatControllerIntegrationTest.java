package net.dancier.chatdancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dancier.chatdancer.dtos.CreateMessageDto;
import net.dancier.chatdancer.dtos.CreateNewChatRequestDto;
import net.dancier.chatdancer.models.ChatType;
import net.dancier.chatdancer.services.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChatControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ChatService chatService;


    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateAChatWorks() throws Exception {
        UUID firstDancerId = UUID.randomUUID();
        UUID secondDancerId = UUID.randomUUID();
        // first assert we have no chat for a given dancer id
        mockMvc.perform(get("/chats").param("dancerId", UUID.randomUUID().toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());

        // make a post to create a new chat
        CreateNewChatRequestDto createNewChatRequestDTO = new CreateNewChatRequestDto();
        createNewChatRequestDTO.setType(ChatType.DIRECT);
        createNewChatRequestDTO.setDancerIds(List.of(firstDancerId, secondDancerId));

        mockMvc.perform(post("/chats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createNewChatRequestDTO)))
                .andExpect(status().isCreated());

        // check with a get, that the chat is really created
        mockMvc.perform(get("/chats").param("dancerId", firstDancerId.toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());

        mockMvc.perform(get("/chats").param("dancerId", secondDancerId.toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());

    }

    @Test
    void postingMessagesToAChatWorks() throws Exception {
        UUID dancerId = UUID.randomUUID();

//        mockMvc.perform(get("/chats").param("dancerId", dancerId.toString()))
//                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());

        // now post message to the chat

        CreateMessageDto createMessageDto = CreateMessageDto.builder().text("testMessage").authorId(dancerId).build();

        mockMvc.perform(post("/chats/{chatId}/messages", "ae30938e-8d5c-4d18-960e-214be61cf83a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(createMessageDto)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.text").value("testMessage"));

    }

    @Test
    void postMessageToANonExistingChatLeadsTo404() throws Exception {
        UUID chatId = UUID.randomUUID();

        mockMvc.perform(post("/chats/{chatId}/messages", chatId)).andExpect(status().isNotFound());

    }

    void creatingANewChatWithoutATypeLeadTo400() throws Exception {


    }

    @Test
    void testGetChatReturnsEmptyList() throws Exception {
        mockMvc.perform(get("/chats").param("dancerId", UUID.randomUUID().toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());
    }


}
