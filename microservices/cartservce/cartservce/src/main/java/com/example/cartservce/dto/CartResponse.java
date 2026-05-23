package com.example.cartservce.dto;

import com.example.cartservce.constants.CartStatus;
import com.example.cartservce.model.CartItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    }
}
