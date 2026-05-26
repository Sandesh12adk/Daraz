package com.example.orderservice.service;

import com.example.orderservice.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service", url = "http://localhost:8083")
public interface CartFeignClient {
    // GET CART
    @GetMapping("Cart/user/{userId}")
    public ResponseEntity<CartResponse> getCartByUserId(@PathVariable int userId);
}
