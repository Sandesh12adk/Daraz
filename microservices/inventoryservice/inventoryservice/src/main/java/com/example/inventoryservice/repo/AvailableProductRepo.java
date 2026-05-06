package com.example.inventoryservice.repo;

import com.example.inventoryservice.model.AvailableProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailableProductRepo extends JpaRepository<AvailableProduct, Integer> {
    public Optional<AvailableProduct> findByProductId(int id);
}
