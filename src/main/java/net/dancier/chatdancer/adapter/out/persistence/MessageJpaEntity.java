package net.dancier.chatdancer.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "message")
@Data
public class MessageJpaEntity {

    @Id
    private UUID id;

    @Column(name = "chat_id")
    private UUID chatId;

    private String text;

    private String authorId;

    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "message_readby", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "participant_id")
    private Set<String> readyBy;

}
