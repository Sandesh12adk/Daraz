package com.example.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = " category")
    private List<Product> productList;
}
