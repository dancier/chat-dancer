package net.dancier.chatdancer.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Chat {

    @NotNull
    private UUID chatId;
    private List<UUID> dancersIds;
    @Nullable
    private Timestamp lastActivity;
    private ChatType type;
    @Nullable
    private Message lastMessage;
    private List<Message> messages;
    private Timestamp creationTimestamp;

}
