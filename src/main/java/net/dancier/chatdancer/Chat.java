package net.dancier.chatdancer;

import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class Chat {
    private UUID chatId;
    private List<UUID> userIds;
    @Nullable
    private String lastActivity;
    private ChatType chatType;
    @Nullable
    private Message last_message;

}
