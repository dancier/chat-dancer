package net.dancier.chatdancer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatJpaRepository extends JpaRepository<JpaChatEntity, UUID> { }
