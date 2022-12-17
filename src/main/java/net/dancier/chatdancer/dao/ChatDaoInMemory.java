package net.dancier.chatdancer.dao;

import net.dancier.chatdancer.models.Chat;
import net.dancier.chatdancer.models.Message;
import net.dancier.chatdancer.utils.NotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class ChatDaoInMemory implements ChatDao {

    List<Message> testMessageList = new ArrayList<>();
    UUID testChatUUID = UUID.fromString("ae30938e-8d5c-4d18-960e-214be61cf83a");
    List<Chat> myChats = new LinkedList<>(List.of(Chat.builder().chatId(testChatUUID).messages(testMessageList).build()));

    @Override
    public Chat createNewChatByParticipants(Chat chat) {
        UUID chatId = UUID.randomUUID();
        myChats.add(Chat.builder()
                .chatId(chatId)
                .messages(new ArrayList<>())
                .type(chat.getType())
                .dancersIds(chat.getDancersIds())
                .creationTimestamp(new Timestamp(new Date().getTime()))
                .build());
        return myChats.get(myChats.size() - 1);

    }

    @Override
    public List<Chat> getAllChatsForDancer(UUID id) {

        List<Chat> usersChats = new ArrayList<>();

        for (Chat chat : myChats) {
            if (chat.getDancersIds() != null && chat.getDancersIds().contains(id)) {
                usersChats.add(chat);
            }
        }
        return usersChats;

    }

    @Override
    public Chat getChatById(UUID chatId) {

        for (Chat chat : myChats) {

            if (chat.getChatId().compareTo(chatId) == 0) {
                return chat;
            }
        }
        throw new NotFoundException(String.format("Chat with id %s not found", chatId));
    }

    @Override
    public List<Message> getAllMessagesForChat(UUID chatId) {


        return getChatById(chatId).getMessages();
    }

    @Override
    public Message createNewMessageForChat(Message message, UUID chatId) {
        Chat chat = getChatById(chatId);
        chat.getMessages().add(Message.builder()
                .id(UUID.randomUUID())
                .chatId(chatId)
                .authorId(message.getAuthorId())
                .text(message.getText())
                .creationTimestamp(new Timestamp(new Date().getTime()))
                .build());

        List<Message> messageList = chat.getMessages();
        chat.setLastMessage(chat.getMessages().get(messageList.size() - 1));
        if (chat.getLastMessage() != null) {
            chat.setLastActivity(chat.getLastMessage().getCreationTimestamp());
        }

        return messageList.get(messageList.size() - 1);
    }


}
