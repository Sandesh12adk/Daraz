package com.example.orderservice.dto;
import com.example.orderservice.constant.CartStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CartResponse {

    private int cartId;
    private int userId;
    private CartStatus cartStatus;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private List<CartItemData> cartItems;

    @Data
    @NoArgsConstructor
    public static class CartItemData {
        private int productId;
        private float productPriceSnapshot;
        private int productQuantity;
        private String name;
        private String description;
    }
}
