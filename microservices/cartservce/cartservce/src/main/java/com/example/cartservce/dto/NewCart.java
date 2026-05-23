package com.example.cartservce.dto;

import com.example.cartservce.constants.CartStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewCart {
    private int cartId;
    private int userId;
    private CartStatus cartStatus;
    private LocalDateTime createdDateTime;
}
