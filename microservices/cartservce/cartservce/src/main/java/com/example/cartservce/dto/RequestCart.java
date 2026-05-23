package com.example.cartservce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCart {

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Integer userId;

    @Min(value = 1, message = "Product ID must be greater than 0")
    private int productId;

    @Min(value = 1, message = "Product  must be greater than 0")
    private int Quantity;
}