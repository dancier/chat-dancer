package net.dancier.chatdancer.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "chat")
@NoArgsConstructor
public class ChatJpaEntity {

    @Id
    private UUID id;

    @ElementCollection
    @CollectionTable(name="chat_participants", joinColumns = @JoinColumn(name = "chat_id"))
    @Column(name = "participant_id")
    private Set<String> participants;

    @OneToMany(cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JoinColumn(name = "chat_id")
    private List<JpaMessageEntity> messages;

    private LocalDateTime createdAt;

}
