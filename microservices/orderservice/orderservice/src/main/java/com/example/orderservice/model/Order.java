package com.example.orderservice.model;

import com.example.orderservice.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqueId;
    @Column(nullable = false)
    private int userid;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Embedded
    private ShippingAddress shippingAddress;
    @OneToMany(orphanRemoval = true,mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList= new ArrayList<>();
}
