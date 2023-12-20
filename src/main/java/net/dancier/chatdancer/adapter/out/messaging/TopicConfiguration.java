package net.dancier.chatdancer.adapter.out.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicConfiguration {

    @Bean
    public KafkaAdmin.NewTopics createTopics() {
        NewTopic chatCreatedTopic = TopicBuilder.name(TopicNames.CHAT_CREATED).build();
        NewTopic messagePosted = TopicBuilder.name(TopicNames.MESSAGE_POSTED).build();
        NewTopic messageRead = TopicBuilder.name(TopicNames.MESSAGE_READ).build();
        return new KafkaAdmin.NewTopics(
                chatCreatedTopic,
                messagePosted,
                messageRead
        );
    }

}
