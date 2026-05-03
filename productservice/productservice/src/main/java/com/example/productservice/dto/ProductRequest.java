package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String desc;
    private float price;
    private int stock;
    private int userid; //. later we will fetch from JWT for now do manually
    private String category;
    private String imageUrl;
    private String brand;
}
