package com.ecommerce.Shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    // Fetch all products under a specific category id
    List<Product> findByCategory_Id(Long categoryId);

    // Search method for partial, case-insensitive matching
    List<Product> findByNameContainingIgnoreCase(String query);
}
