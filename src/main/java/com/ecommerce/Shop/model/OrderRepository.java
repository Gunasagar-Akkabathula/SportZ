package com.ecommerce.Shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUserEmailOrderByCreatedAtDesc(String userEmail);
    
    Order findByRazorpayOrderId(String razorpayOrderId);
    
    // NEW: Check if user has purchased a specific product
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
           "FROM Order o " +
           "WHERE o.userEmail = :userEmail " +
           "AND o.orderItems LIKE CONCAT('%\"id\":', :productId, '%') " +
           "AND o.orderStatus IN ('PLACED', 'SHIPPED', 'DELIVERED')")
    boolean hasUserPurchasedProduct(@Param("userEmail") String userEmail, 
                                    @Param("productId") Long productId);
}



