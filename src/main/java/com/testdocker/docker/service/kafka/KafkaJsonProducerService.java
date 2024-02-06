package com.testdocker.docker.service.kafka;

import com.testdocker.docker.entity.LogCrudEntity;
import com.testdocker.docker.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonProducerService.class);

    private final KafkaTemplate<String, LogCrudEntity> kafkaTemplate;

    public KafkaJsonProducerService(KafkaTemplate<String, LogCrudEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(LogCrudEntity logCrudEntity){
        // Ex. User Created -> ID: 1
        String log = String.format("Event Produced: %s %s -> ID: %s", logCrudEntity.getDataType(), logCrudEntity.getAction(), logCrudEntity.getDataId());
        LOGGER.info(log);

        Message<LogCrudEntity> message = MessageBuilder
                .withPayload(logCrudEntity)
                .setHeader(KafkaHeaders.TOPIC, "user-events")
                .build();
        kafkaTemplate.send(message);
    }
}
