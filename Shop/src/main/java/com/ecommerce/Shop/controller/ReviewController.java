package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.model.Review;
import com.ecommerce.Shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;
    
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getProductReviews(productId);
        return ResponseEntity.ok(reviews);
    }
    
    @GetMapping("/product/{productId}/summary")
    public ResponseEntity<Map<String, Object>> getProductRatingSummary(@PathVariable Long productId) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("averageRating", reviewService.getAverageRating(productId));
        summary.put("reviewCount", reviewService.getReviewCount(productId));
        return ResponseEntity.ok(summary);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getUserReviews(userId);
        return ResponseEntity.ok(reviews);
    }
    
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        Review updated = reviewService.updateReview(reviewId, review);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Review deleted successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/check/{userId}/{productId}")
    public ResponseEntity<Map<String, Boolean>> checkUserReview(
            @PathVariable Long userId, 
            @PathVariable Long productId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("hasReviewed", reviewService.hasUserReviewed(userId, productId));
        return ResponseEntity.ok(response);
    }
}

