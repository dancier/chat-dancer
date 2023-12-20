package net.dancier.chatdancer.adapter.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Transactional
    @Scheduled(fixedRate = 2000)
    public void sendMessages() throws UnsupportedEncodingException, JsonProcessingException {
        Collection<OutboxJpaEntity> itemsToBeProcesses = outboxJpaRepository.lockAndList();
        for(OutboxJpaEntity entity: itemsToBeProcesses) {
            sendMessage(entity);
        }
    }

    public void sendMessage(OutboxJpaEntity entity) throws UnsupportedEncodingException, JsonProcessingException {
        log.info("sending: " + entity);
        entity.setStatus(OutboxJpaEntity.STATUS.DONE);
        sendOutboxService.send(entity);
        outboxJpaRepository.save(entity);
    }

}
