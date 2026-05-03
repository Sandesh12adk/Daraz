package com.example.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class Product {
    private int id;
    private String name;
    private String desc;
    private float price;
    private int stock;
    private int userid;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "product")
    private List<Image> imageList;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
