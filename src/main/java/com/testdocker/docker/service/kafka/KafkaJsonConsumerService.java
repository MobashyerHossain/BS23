package com.testdocker.docker.service.kafka;

import com.testdocker.docker.entity.LogCrudEntity;
import com.testdocker.docker.service.LogCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonConsumerService.class);

    private final LogCrudService LogCrudService;

    @Autowired
    public KafkaJsonConsumerService(com.testdocker.docker.service.LogCrudService logCrudService) {
        LogCrudService = logCrudService;
    }

    @KafkaListener(topics = "user-events", groupId = "bs23Group")
    public void consumerMessage(LogCrudEntity logCrudEntity){
        try {
            LOGGER.info(String.format("Event Detected -> (%s)", logCrudEntity));
            LogCrudService.store(logCrudEntity);
        }catch (Exception e){
            LOGGER.info(String.format("Kafka Consumer Error -> (%s)", e.getMessage()));
        }

    }
}
