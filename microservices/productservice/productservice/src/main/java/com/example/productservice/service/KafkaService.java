package com.example.productservice.service;

import com.example.productservice.event.KafkaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;
    @Autowired
    public KafkaService(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductCreatedEvent(int productId) {
        KafkaEvent event = new KafkaEvent();
        event.setProductId(productId);

        System.out.println("Sending Kafka event to product-created: " + productId);

        kafkaTemplate.send("product-created", event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println("Kafka send failed: " + ex.getMessage());
                    } else {
                        System.out.println("Kafka send success");
                        System.out.println("Topic: " + result.getRecordMetadata().topic());
                        System.out.println("Partition: " + result.getRecordMetadata().partition());
                        System.out.println("Offset: " + result.getRecordMetadata().offset());
                    }
                });
    }
    public void sendProductDeletedEvent(int productId){
        KafkaEvent event= new KafkaEvent();
        event.setProductId(productId);
        kafkaTemplate.send("product-deleted", event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println("Kafka send failed: " + ex.getMessage());
                    } else {
                        System.out.println("Kafka send success");
                        System.out.println("Topic: " + result.getRecordMetadata().topic());
                        System.out.println("Partition: " + result.getRecordMetadata().partition());
                        System.out.println("Offset: " + result.getRecordMetadata().offset());
                    }
                });
    }
}