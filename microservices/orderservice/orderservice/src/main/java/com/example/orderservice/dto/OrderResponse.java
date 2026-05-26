package com.example.orderservice.dto;

import com.example.orderservice.constant.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponse {
    private int orderId;

    private int userId;

    private OrderStatus orderStatus;

    private float totalAmount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ShippingAddressDTO shippingAddress;

    private List<OrderItemDTO> orderItems;

    @Data
    @NoArgsConstructor
    public static class ShippingAddressDTO {
        private String country;
        private String province;
        private String district;
        private String city;
        private String street;
    }

    @Data
    @NoArgsConstructor
    public static class OrderItemDTO {
        private int productId;
        private String productNameSnapshot;
        private int quantity;
        private float unitPriceSnapshot;
        private float totalPrice;
    }
    }
