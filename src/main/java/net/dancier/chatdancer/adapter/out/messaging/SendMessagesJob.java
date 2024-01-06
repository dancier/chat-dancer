package net.dancier.chatdancer.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class SendMessagesJob {

    private Logger log = LoggerFactory.getLogger(SendMessagesJob.class);

    private final OutboxJpaRepository outboxJpaRepository;

    private final SendOutboxService sendOutboxService;

    private final KafkaTemplate kafkaTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessages() throws JsonProcessingException {
        Collection<OutboxJpaEntity> itemsToBeProcesses = outboxJpaRepository.lockAndList();
        for(OutboxJpaEntity entity: itemsToBeProcesses) {
            sendMessage(entity);
        }
        kafkaTemplate.flush();
    }

    @Transactional
    public void sendMessage(OutboxJpaEntity entity) throws JsonProcessingException {
        log.info("sending: " + entity);
        sendOutboxService.send(entity);
        entity.setStatus(OutboxJpaEntity.STATUS.DONE);
        log.info("success: " + entity);
        outboxJpaRepository.save(entity);
    }

}
