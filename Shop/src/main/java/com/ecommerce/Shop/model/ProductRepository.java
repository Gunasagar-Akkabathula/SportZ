package com.ecommerce.Shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    // Add this to fetch all products under a specific category id
    List<Product> findByCategory_Id(Long categoryId);

    // If you want to fetch by category entity itself
    // List<Product> findByCategory(Category category);
}


