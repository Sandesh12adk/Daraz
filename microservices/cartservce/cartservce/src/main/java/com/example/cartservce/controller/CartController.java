package com.example.cartservce.controller;

import com.example.cartservce.dto.CartResponse;
import com.example.cartservce.dto.NewCart;
import com.example.cartservce.dto.RequestCart;
import com.example.cartservce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // ADD / CREATE CART ITEM
    @PostMapping("/create")
    public ResponseEntity<NewCart> createCart(@Valid @RequestBody RequestCart requestCart) {
        return ResponseEntity.ok(cartService.createNewCart(requestCart));
    }

    // GET CART
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> getCartByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.findByUserId(userId));
    }

    // INCREASE QUANTITY
    @PutMapping("/item/increase")
    public ResponseEntity<CartResponse> increaseQuantity(
            @RequestParam int userId,
            @RequestParam int productId,
            @RequestParam int quantity) {

        return ResponseEntity.ok(cartService.increaseQuantity(userId, productId, quantity));
    }

    // DECREASE QUANTITY
    @PutMapping("/item/decrease")
    public ResponseEntity<CartResponse> decreaseQuantity(
            @RequestParam int userId,
            @RequestParam int productId,
            @RequestParam int quantity) {

        return ResponseEntity.ok(cartService.decreaseQuantity(userId, productId, quantity));
    }

    // REMOVE ITEM
    @DeleteMapping("/item")
    public ResponseEntity<CartResponse> removeItem(
            @RequestParam int userId,
            @RequestParam int productId) {

        return ResponseEntity.ok(cartService.removeItem(userId, productId));
    }

    // CLEAR CART
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable int userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }

    // CART SUMMARY
    @GetMapping("/summary/{userId}")
    public ResponseEntity<?> getSummary(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.getCartSummary(userId));
    }

    // CHECKOUT
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<String> checkout(@PathVariable int userId) {
        cartService.checkout(userId);
        return ResponseEntity.ok("Checkout successful");
    }
}