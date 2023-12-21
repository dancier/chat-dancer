package net.dancier.chatdancer;

import net.dancier.chatdancer.adapter.in.web.*;
import net.dancier.chatdancer.adapter.in.web.controller.CreateChatController;
import net.dancier.chatdancer.adapter.in.web.controller.CreatedChatMessageDto;
import net.dancier.chatdancer.adapter.in.web.controller.SetReadFlagRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatSystemTest extends AbstractPostgreSQLEnabledTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testCreateAndGetChat() {

        // No chat has been created so when searching for a chat of participant foo, we should get an empty list
        ResponseEntity<GetChatResponseDto[]> chatsByParticipantsResponse = whenGetChatsByParticipantsIsBeingInvoked("foo");
        then(chatsByParticipantsResponse).isNotNull();
        then(Arrays.stream(chatsByParticipantsResponse.getBody()).toList()).isEmpty();

        // After we created a chat we should be able to geht the chat
        PostChatRequestDto postChatDto = new PostChatRequestDto();
        postChatDto.setParticipantIds(List.of("foo", "bar"));
        ResponseEntity<CreatedChatDto> createdChatResponse = whenCreateChatEndpointIsBeingInvoked(postChatDto);

        then(createdChatResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        then(createdChatResponse.getBody().getId()).isNotNull();

        // extract what chat has been created (its URI)
        String createChatResource = createdChatResponse.getHeaders().getLocation().toString();
        then(createChatResource).isNotNull();

        // Now check that we could get the chat
        ResponseEntity<GetChatResponseDto> getChatResponse = whenGetChatEndpointIsBeingInvoked(createChatResource);
        then(getChatResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(createChatResource).endsWith(getChatResponse.getBody().getChatId().toString());
        then(getChatResponse.getBody().getCreatedAt()).isNotNull();
        then(getChatResponse.getBody().getParticipantIds().size()).isEqualTo(2);
        then(getChatResponse.getBody().getLastMessage()).isNull();
        then(getChatResponse.getBody().getLastActivity()).isNull();

        // We should now also be able lookup the chat by one of its participants
        // (while no chat-message was posted to that chat
        chatsByParticipantsResponse = whenGetChatsByParticipantsIsBeingInvoked("foo");
        then(chatsByParticipantsResponse).isNotNull();
        then(Arrays.stream(chatsByParticipantsResponse.getBody()).toList()).isNotEmpty();
        then(Arrays.stream(chatsByParticipantsResponse.getBody()).toList().get(0).getLastMessage()).isNull();


        // Now we post a chat message
        ResponseEntity<CreatedChatMessageDto> createdChatMessageResponse = whenPostChatMessageEndpointIsBeingInvoked(
                getChatResponse.getBody().getChatId().toString(),
                "Hallo World",
                "bar"
                );
        then(createdChatMessageResponse.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        then(createdChatMessageResponse.getBody()).isNotNull();

        String idOfGeneratedChatMessageForFurtherRef = createdChatMessageResponse.getBody().getId();
        then(idOfGeneratedChatMessageForFurtherRef).isNotNull();
        then(createdChatMessageResponse.getHeaders().getLocation().toString()).endsWith(idOfGeneratedChatMessageForFurtherRef);


        // now we can assert that
        // 1) the message will pop up in the chat overview via the get chat endpoint
        // 2) when we finde that chat by participant
        // 3) when getting all chat messages from the chat


        // for 1) getting if via get-chat-endpoint
        ResponseEntity<GetChatResponseDto> chatResponseWithMessageIncluded = whenGetChatEndpointIsBeingInvoked(createChatResource);
        then(chatResponseWithMessageIncluded).isNotNull();
        then(chatResponseWithMessageIncluded.getBody().getLastMessage()).isNotNull();
        then(chatResponseWithMessageIncluded.getBody().getLastActivity()).isNotNull();

        // for 2) getting it by participant

        ResponseEntity<GetChatResponseDto[]> lookupByParticipantResponse = whenGetChatsByParticipantsIsBeingInvoked("bar");
        then(Arrays.stream(lookupByParticipantResponse.getBody()).toList().get(0).getLastMessage().getText()).contains("World");

        // for 3)

        ResponseEntity<MessageDto[]> allMessagesFromTheChatResponse = whenGetMessagesForAChatIsInvoked(chatResponseWithMessageIncluded.getBody().getChatId().toString());
        then(allMessagesFromTheChatResponse).isNotNull();
        then(allMessagesFromTheChatResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(Arrays.stream(allMessagesFromTheChatResponse.getBody()).toList().get(0).getText()).contains("World");
        then(Arrays.stream(allMessagesFromTheChatResponse.getBody()).toList().get(0).getId()).isEqualTo(idOfGeneratedChatMessageForFurtherRef);

        // assert that the message has not been read by anyone
        then(Arrays.stream(allMessagesFromTheChatResponse.getBody()).toList().get(0).getReadByParticipants()).isEmpty();

        ResponseEntity responseAfterSetTheReadFlag = whenPutReadFlagEndpointIsBeingInvoked(idOfGeneratedChatMessageForFurtherRef, "bar", Boolean.TRUE);
        then(responseAfterSetTheReadFlag.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

    }

    private ResponseEntity whenPutReadFlagEndpointIsBeingInvoked(String messageId, String participantId, Boolean read) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        SetReadFlagRequestDto setReadFlagRequestDto = new SetReadFlagRequestDto();
        setReadFlagRequestDto.setRead(Boolean.TRUE);

        HttpEntity<SetReadFlagRequestDto> request = new HttpEntity<>(setReadFlagRequestDto, headers);

        return testRestTemplate.exchange(
                String.format("/messages/%s/read-by/%s", messageId,participantId),
                HttpMethod.PUT,
                request,
                Object.class
        );
    }

    private ResponseEntity<CreatedChatMessageDto> whenPostChatMessageEndpointIsBeingInvoked(String chatId, String text, String author) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PostChatMessageRequestDto postChatMessageRequestDto = PostChatMessageRequestDto
                .builder()
                .authorId(author)
                .text(text)
                .build();
        HttpEntity<PostChatMessageRequestDto> request = new HttpEntity<>(postChatMessageRequestDto, headers);

        return testRestTemplate.exchange(
                String.format("/chats/%s/messages", chatId),
                HttpMethod.POST,
                request,
                CreatedChatMessageDto.class
        );
    }

    private ResponseEntity<MessageDto[]> whenGetMessagesForAChatIsInvoked(String chatId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(headers);

        return testRestTemplate.exchange(
                String.format("/chats/%s/messages", chatId),
                HttpMethod.GET,
                request,
                MessageDto[].class
        );
    }



    private ResponseEntity whenGetChatEndpointIsBeingInvoked(String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(headers);
        return testRestTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                GetChatResponseDto.class
        );
    }

    private ResponseEntity<CreatedChatDto> whenCreateChatEndpointIsBeingInvoked(PostChatRequestDto postChatDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<PostChatRequestDto> request = new HttpEntity<>(postChatDto, headers);
        return testRestTemplate.exchange(
                CreateChatController.CREATE_CHAT_ENDPOINT,
                HttpMethod.POST,
                request,
                CreatedChatDto.class
        );
    }

    private ResponseEntity<GetChatResponseDto[]> whenGetChatsByParticipantsIsBeingInvoked(String participantId) {
        String uriTemplate = "/chats?participantId={participantId}";
        Map<String, String> getParameter = Map.of("participantId", participantId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity request = new HttpEntity(headers);
        return testRestTemplate.exchange(
                uriTemplate,
                HttpMethod.GET,
                request,
                GetChatResponseDto[].class,
                getParameter
        );
    }
}
