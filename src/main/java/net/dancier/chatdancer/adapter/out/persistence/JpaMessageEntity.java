package net.dancier.chatdancer.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message")
@Data
public class JpaMessageEntity {

    @Id
    private UUID id;

    @Column(name = "chat_id")
    private UUID chatId;

    private String text;

    private String authorId;

    private LocalDateTime createdAt;


}
