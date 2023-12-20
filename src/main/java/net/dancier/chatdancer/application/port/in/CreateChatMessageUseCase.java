package net.dancier.chatdancer.application.port.in;

public interface CreateChatMessageUseCase {

    void post(CreateChatMessageCommand command);

}
