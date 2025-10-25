package com.ecommerce.Shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByProductIdOrderByCreatedAtDesc(Long productId);
    
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    Optional<Review> findByUserIdAndProductId(Long userId, Long productId);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productId = :productId")
    Double getAverageRating(@Param("productId") Long productId);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.productId = :productId")
    Long getReviewCount(@Param("productId") Long productId);
}

