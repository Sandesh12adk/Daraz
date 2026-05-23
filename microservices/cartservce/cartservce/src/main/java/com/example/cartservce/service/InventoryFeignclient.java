package com.example.cartservce.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "inventory-service",
        url = "http://localhost:8081"
)
public interface InventoryFeignclient {
    @GetMapping("/inventory/check") // it check for total quantity - reserved_quantity and throw resource not found if invalid product id
    public ResponseEntity<Boolean> checkIfAvailable(@RequestParam int productId, @RequestParam int  Availablequantity);
}
