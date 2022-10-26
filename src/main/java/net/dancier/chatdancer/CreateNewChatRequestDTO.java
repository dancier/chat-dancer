package net.dancier.chatdancer;

import lombok.Data;
import org.apache.logging.log4j.message.Message;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class CreateNewChatRequestDTO {

     private UUID chatId;
     private List<UUID> userIds;
     private Timestamp lastActivity;
     private ChatType chatType;
     private Message last_message;
}
