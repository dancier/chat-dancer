package net.dancier.chatdancer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatJpaRepository extends JpaRepository<JpaChatEntity, UUID> {


    @Query(nativeQuery = true,
                 value = """
                 select *
                   from chat
             inner join chat_participants
                     on chat.id = chat_participants.chat_id
               where participant_id = :participantId ;
            """)
    List<JpaChatEntity> findByParticipant(@Param("participantId") String participantId);

}
