package com.example.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Image {
    private  int id;
    private String url;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
