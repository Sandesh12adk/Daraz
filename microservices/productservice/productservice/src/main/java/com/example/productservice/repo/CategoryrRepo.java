package com.example.productservice.repo;

import com.example.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryrRepo extends JpaRepository<Category, Integer> {
    public Optional<Category> findByName(String name);
}
