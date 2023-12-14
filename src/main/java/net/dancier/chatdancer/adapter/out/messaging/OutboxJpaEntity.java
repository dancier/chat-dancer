package net.dancier.chatdancer.adapter.out.messaging;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "outbox")
@NoArgsConstructor
public class OutboxJpaEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String topic;

    @JdbcTypeCode(SqlTypes.JSON)
    private String metaData;

    private String key;

    @JdbcTypeCode(SqlTypes.JSON)
    private String payload;

    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private STATUS status;

    public static enum STATUS {
        NEW,
        IN_PROGRESS,
        TEMP_FAILED,
        FINALLY_FAILED
    }

}
