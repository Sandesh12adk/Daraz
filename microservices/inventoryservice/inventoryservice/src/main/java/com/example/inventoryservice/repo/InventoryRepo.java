package com.example.inventoryservice.repo;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.jar.JarEntry;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Integer> {
    public Optional<Inventory> findByProductId(int productId);
}
