package com.ecommerce.Shop.service;

import com.ecommerce.Shop.model.Wishlist;
import com.ecommerce.Shop.model.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WishlistService {
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    public Wishlist addToWishlist(Long userId, Long productId) {
        if (wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
            return null;
        }
        Wishlist wishlist = new Wishlist(userId, productId);
        return wishlistRepository.save(wishlist);
    }
    
    public List<Wishlist> getUserWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }
    
    @Transactional
    public boolean removeFromWishlist(Long userId, Long productId) {
        if (wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
            wishlistRepository.deleteByUserIdAndProductId(userId, productId);
            return true;
        }
        return false;
    }
    
    public boolean isInWishlist(Long userId, Long productId) {
        return wishlistRepository.existsByUserIdAndProductId(userId, productId);
    }
    
    @Transactional
    public void clearWishlist(Long userId) {
        List<Wishlist> items = wishlistRepository.findByUserId(userId);
        wishlistRepository.deleteAll(items);
    }
}

