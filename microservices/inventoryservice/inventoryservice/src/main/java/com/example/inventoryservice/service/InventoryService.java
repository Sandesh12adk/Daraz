package com.example.inventoryservice.service;

import com.example.inventoryservice.controller.InventoryController;
import com.example.inventoryservice.dto.InventoryRequest;
import com.example.inventoryservice.exception.OutOfStockException;
import com.example.inventoryservice.exception.ResourceNotFoundException;
import com.example.inventoryservice.mapping.InventoryRequestToInventory;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    private final InventoryRequestToInventory inventoryRequestToInventory;
    @Autowired
    public InventoryService(InventoryRepo inventoryRepo, InventoryRequestToInventory i){
        this.inventoryRepo= inventoryRepo;
        this.inventoryRequestToInventory= i;
    }
    public void add(InventoryRequest inventoryRequest){
        inventoryRepo.save(inventoryRequestToInventory.convert(inventoryRequest));
    }
    public Integer increase(int productId, int increaseBy){
        Inventory inventory= inventoryRepo.findByProductId(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Cannot find the inventory with productId"+ productId));
        Integer updatedQuantity= inventory.getQuantity()+ increaseBy;
        //we are using same code/ controller to decresase the quantity also to reduce the quantity by providingthe negative value
        if(updatedQuantity<=0){
            throw  new OutOfStockException("Product is already empty cannot decrease anymore");
        }
        inventory.setQuantity(updatedQuantity);
        return updatedQuantity;

    }

    public Integer reserve(int productId, int reserveQuantity) {
        Inventory inventory= inventoryRepo.findByProductId(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Cannot find the inventory with productId"+ productId));
        int availabeQuantity=inventory.getQuantity();
        Integer updatedReserveQuantity= inventory.getReserved_quantity()+ reserveQuantity;
        if(updatedReserveQuantity>availabeQuantity){
            throw new OutOfStockException("Out of stock");
        }
        inventory.setReserved_quantity(reserveQuantity);
        return updatedReserveQuantity;
    }

    public Boolean checkIfAvailabe( int productId, int quantity) {
        Inventory inventory= inventoryRepo.findByProductId(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Cannot find the inventory with productId"+ productId));
        return (inventory.getQuantity()-inventory.getReserved_quantity()>=quantity)? true : false;
    }
}
