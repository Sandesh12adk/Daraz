package com.example.productservice.repo;

import com.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findBySellerId(int userid);

    List<Product> findByCategory_Name(String name);

    List<Product> findByBrand_Name(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
