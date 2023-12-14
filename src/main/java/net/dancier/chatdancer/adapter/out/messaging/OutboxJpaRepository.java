package net.dancier.chatdancer.adapter.out.messaging;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxJpaRepository extends JpaRepository<OutboxJpaEntity, UUID> {
}
