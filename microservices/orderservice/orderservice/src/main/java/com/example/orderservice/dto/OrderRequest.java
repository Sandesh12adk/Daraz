package com.example.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequest {
    private int userId;
    private String country;
    private String province;
    private String district;
    private String city;
    private String street;
}
