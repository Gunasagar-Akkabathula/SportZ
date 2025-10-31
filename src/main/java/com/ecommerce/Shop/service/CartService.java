package com.ecommerce.Shop.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.Shop.model.Cart;
import com.ecommerce.Shop.model.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addOrUpdateCartItem(String userEmail, String itemName, int quantity, Double price, String imageUrl) {
        Cart existing = cartRepository.findByUserEmailAndItemName(userEmail, itemName);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            // Update price and image in case they changed
            existing.setPrice(price);
            existing.setImageUrl(imageUrl);
            return cartRepository.save(existing);
        } else {
            Cart cart = new Cart(userEmail, itemName, quantity, price, imageUrl);
            return cartRepository.save(cart);
        }
    }

    public List<Cart> getCartByUserEmail(String email) {
        return cartRepository.findByUserEmail(email);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
    
    @Transactional
    public void clearCart(String userEmail) {
        List<Cart> carts = cartRepository.findByUserEmail(userEmail);
        cartRepository.deleteAll(carts);
    }
}
