package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryRequest;
import com.example.inventoryservice.mapping.InventoryRequestToInventory;
import com.example.inventoryservice.repo.InventoryRepo;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryRequestToInventory inventoryRequestToInventory;

    @PostMapping("/add")
    public ResponseEntity<Void> addInventory(InventoryRequest inventoryRequest){
        inventoryService.add(inventoryRequest);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/increase ")  //Can be use to decrease also by giving the incresedBy value in negative
    public ResponseEntity<Integer> increaseQuantity(@RequestParam int prooductId, @RequestParam int increaseBy){
                return ResponseEntity.ok(inventoryService.increase(prooductId,increaseBy));
    }
    @PutMapping("/reserve")// total quantity will not be decreased on reserving we calculate availane at run time
    public ResponseEntity<Integer> reserveQuantity(@RequestParam int productId, @RequestParam int reserveQuantity){
        return ResponseEntity.ok(inventoryService.reserve(productId,reserveQuantity));
    }
    @GetMapping("check/{productId}") // it check for total quantity - reserved_quantity
    public ResponseEntity<Boolean> checkIfAvailable(@RequestParam int productId, @RequestParam int quantity){
        return ResponseEntity.ok(inventoryService.checkIfAvailabe(productId, quantity));
    }
}
