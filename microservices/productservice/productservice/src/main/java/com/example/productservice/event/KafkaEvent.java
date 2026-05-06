package com.example.productservice.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KafkaEvent {
    private int productId;
}
