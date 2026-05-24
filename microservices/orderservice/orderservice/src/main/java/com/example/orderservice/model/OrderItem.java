package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqueId;
    private int productId;
    private String productNameSnapshot;
    private int quantity;
    private float unitPriceSnapshot;
    @ManyToOne
    @JoinColumn(name= "order_id")
    private Order order;
}