package com.example.productservice.repo;

import com.example.productservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepo extends JpaRepository<Brand, Integer> {
    public Optional<Brand> findByname(String name);
}
