package com.ecommerce.Shop.service;

import com.ecommerce.Shop.model.Review;
import com.ecommerce.Shop.model.ReviewRepository;
import com.ecommerce.Shop.model.OrderRepository;
import com.ecommerce.Shop.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Review addReview(Review review) {
        String userEmail = getUserEmailById(review.getUserId());
        boolean hasPurchased = orderRepository.hasUserPurchasedProduct(userEmail, review.getProductId());
        review.setVerified(hasPurchased);
        return reviewRepository.save(review);
    }
    
    public List<Review> getProductReviews(Long productId) {
        return reviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }
    
    public List<Review> getUserReviews(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public Double getAverageRating(Long productId) {
        Double avg = reviewRepository.getAverageRating(productId);
        return avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }
    
    public Long getReviewCount(Long productId) {
        return reviewRepository.getReviewCount(productId);
    }
    
    public Review updateReview(Long reviewId, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(reviewId);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setRating(updatedReview.getRating());
            review.setComment(updatedReview.getComment());
            return reviewRepository.save(review);
        }
        return null;
    }
    
    public boolean deleteReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
    
    public boolean hasUserReviewed(Long userId, Long productId) {
        return reviewRepository.findByUserIdAndProductId(userId, productId).isPresent();
    }
    
    private String getUserEmailById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getEmail())
                .orElse("");
    }
}

