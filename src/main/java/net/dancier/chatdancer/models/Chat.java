package net.dancier.chatdancer.models;

import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
@Data
@Builder
public class Chat {
    private UUID chatId;
    private List<UUID> dancersIds;
    @Nullable
    private Timestamp lastActivity;
    private ChatType chatType;
    @Nullable
    private Message lastMessage;

}
