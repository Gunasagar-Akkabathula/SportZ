package com.ecommerce.Shop.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.Shop.model.Cart;
import com.ecommerce.Shop.model.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addOrUpdateCartItem(String userEmail, String itemName, int quantity) {
        Cart existing = cartRepository.findByUserEmailAndItemName(userEmail, itemName);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            return cartRepository.save(existing);
        } else {
            Cart cart = new Cart(userEmail, itemName, quantity);
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
}
