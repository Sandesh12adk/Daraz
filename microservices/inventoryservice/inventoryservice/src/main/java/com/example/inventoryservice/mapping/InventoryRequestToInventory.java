package com.example.inventoryservice.mapping;

import com.example.inventoryservice.dto.InventoryRequest;
import com.example.inventoryservice.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryRequestToInventory {
    public Inventory convert(InventoryRequest inventoryRequest){
        Inventory inventory= new Inventory();
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setReserved_quantity(0);
        return inventory;
    }
}
