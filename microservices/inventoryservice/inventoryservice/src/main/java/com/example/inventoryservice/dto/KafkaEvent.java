package com.example.inventoryservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KafkaEvent {
    private int productId;
}

