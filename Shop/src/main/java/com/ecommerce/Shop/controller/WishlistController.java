package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.model.Wishlist;
import com.ecommerce.Shop.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {
    
    @Autowired
    private WishlistService wishlistService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> addToWishlist(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long productId = request.get("productId");
        
        Wishlist wishlist = wishlistService.addToWishlist(userId, productId);
        
        Map<String, Object> response = new HashMap<>();
        if (wishlist != null) {
            response.put("success", true);
            response.put("message", "Added to wishlist");
            response.put("wishlist", wishlist);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Already in wishlist");
            return ResponseEntity.ok(response);
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getUserWishlist(@PathVariable Long userId) {
        List<Wishlist> wishlist = wishlistService.getUserWishlist(userId);
        return ResponseEntity.ok(wishlist);
    }
    
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> removeFromWishlist(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long productId = request.get("productId");
        
        boolean removed = wishlistService.removeFromWishlist(userId, productId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", removed);
        response.put("message", removed ? "Removed from wishlist" : "Item not found");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/check/{userId}/{productId}")
    public ResponseEntity<Map<String, Boolean>> checkWishlist(
            @PathVariable Long userId, 
            @PathVariable Long productId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("inWishlist", wishlistService.isInWishlist(userId, productId));
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<Map<String, String>> clearWishlist(@PathVariable Long userId) {
        wishlistService.clearWishlist(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Wishlist cleared");
        return ResponseEntity.ok(response);
    }
}

