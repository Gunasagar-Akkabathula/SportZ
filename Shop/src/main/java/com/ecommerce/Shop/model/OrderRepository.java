package com.ecommerce.Shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmailOrderByCreatedAtDesc(String userEmail);
    Order findByRazorpayOrderId(String razorpayOrderId);
}

