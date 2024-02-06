package com.testdocker.docker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    public NewTopic testKafkaTopic(){
        return TopicBuilder.name("user-events")
                .build();
    }
}
