package net.dancier.chatdancer.adapter.in;

import lombok.RequiredArgsConstructor;
import net.dancier.chatdancer.application.port.in.CreateChatCommand;
import net.dancier.chatdancer.application.port.in.CreateChatUseCase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateChatController {

    private final CreateChatUseCase createChatUseCase;

    @PostMapping("/h/chats")
    void createChat(@Validated @RequestBody CreateChatDto createChatDto) {
        System.out.println("foo");
        CreateChatCommand createChatCommand = new CreateChatCommand(
            createChatDto.getDancerIds().stream().map(uuid -> new CreateChatCommand.ParticipantId(uuid.toString())).toList()
        );
        System.out.println(createChatCommand);
        createChatUseCase.createChat(createChatCommand);
    }

}
