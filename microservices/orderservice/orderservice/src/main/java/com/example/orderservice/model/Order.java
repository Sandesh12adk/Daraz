package com.example.orderservice.model;

import com.example.orderservice.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqueId;
    @Column(nullable = false)
    private int userid;
    @Column(nullable = false)
    private OrderStatus orderStatus;
    @Column(nullable = false)
    private float totalAmount;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @Embedded
    private ShippingAddress shippingAddress;
    @OneToMany(orphanRemoval = true,mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList= new ArrayList<>();
}
