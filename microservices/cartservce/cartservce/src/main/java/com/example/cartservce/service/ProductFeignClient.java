package com.example.cartservce.service;


import com.example.cartservce.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8080"
)
public interface ProductFeignClient {
   @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable int id);
}
