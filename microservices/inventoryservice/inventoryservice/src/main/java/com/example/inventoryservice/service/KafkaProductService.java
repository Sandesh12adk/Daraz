package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.KafkaEvent;
import com.example.inventoryservice.model.AvailableProduct;
import com.example.inventoryservice.repo.AvailableProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaProductService {
    private static final Logger log = LoggerFactory.getLogger(KafkaProductService.class);
    @Autowired
    private AvailableProductRepo availableProductRepo;


    @KafkaListener(topics = "product-created", groupId = "inventory-group-v2")
    public void addId(KafkaEvent kafkaEvent){

        log.info("Kafka event received: {}", kafkaEvent);

        if (availableProductRepo.findByProductId(kafkaEvent.getProductId()).isPresent()){
            log.info("Product already exists: {}", kafkaEvent.getProductId());
            return;
        }

        AvailableProduct product = new AvailableProduct();
        product.setProductId(kafkaEvent.getProductId());

        availableProductRepo.save(product);

        log.info("Saved productId: {}", product.getProductId());
    }
    @KafkaListener(topics = "product-deleted", groupId = "inventory-group-v2")
    public void deleteId(KafkaEvent kafkaEvent){
        if (!availableProductRepo.findByProductId(kafkaEvent.getProductId()).isPresent()){
            log.info("Product already exists: {}", kafkaEvent.getProductId());
            return;
        }
        AvailableProduct product = availableProductRepo.findByProductId(kafkaEvent.getProductId()).get();
        availableProductRepo.delete(product);
    }
}
