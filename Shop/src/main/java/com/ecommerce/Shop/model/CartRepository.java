package com.ecommerce.Shop.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserEmail(String userEmail);

    Cart findByUserEmailAndItemName(String userEmail, String itemName);  // New
}

