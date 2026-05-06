package com.example.productservice.service;

import com.example.productservice.event.KafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductCreatedEvent(int productId) {
        KafkaEvent event = new KafkaEvent();
        event.setProductId(productId);

        kafkaTemplate.send("product-created", event);
    }
}