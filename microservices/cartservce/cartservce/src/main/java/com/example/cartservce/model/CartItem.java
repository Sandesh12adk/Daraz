package com.example.cartservce.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    private int productId;
    private float productPriceSnapshot;
    private int productQuantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}