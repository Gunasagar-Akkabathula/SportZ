package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.model.Cart;
import com.ecommerce.Shop.model.Product;
import com.ecommerce.Shop.service.CartService;
import com.ecommerce.Shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam String userEmail, @RequestBody Map<String, Object> payload) {
        // Prevent adding to cart if not signed-in
        if (userEmail == null || userEmail.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required");
        }

        String itemName = (String) payload.get("itemName");
        int quantity = (int) payload.getOrDefault("quantity", 1);

        cartService.addOrUpdateCartItem(userEmail, itemName, quantity);

        List<Cart> userCarts = cartService.getCartByUserEmail(userEmail);
        int cartCount = userCarts.stream().mapToInt(Cart::getQuantity).sum();

        Map<String, Integer> response = new HashMap<>();
        response.put("cartCount", cartCount);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getCartByUser(@PathVariable String userEmail) {
        List<Cart> carts = cartService.getCartByUserEmail(userEmail);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Cart cartItem : carts) {
            Product product = productService.getProductByName(cartItem.getItemName());
            if (product != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", cartItem.getId());
                map.put("item_name", cartItem.getItemName());
                map.put("quantity", cartItem.getQuantity());
                map.put("price", product.getPrice());
                map.put("image_url", product.getImageUrl());
                response.add(map);
            }
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCartQuantity(@RequestBody Map<String, Object> payload) {
        Long id = ((Number) payload.get("id")).longValue();
        int quantity = (int) payload.get("quantity");
        Optional<Cart> cartOpt = cartService.findById(id);
        if (cartOpt.isPresent()) {
            Cart cartItem = cartOpt.get();
            cartItem.setQuantity(quantity);
            cartService.saveCart(cartItem);
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long id) {
        Optional<Cart> cartOpt = cartService.findById(id);
        if (cartOpt.isPresent()) {
            cartService.deleteCart(id);
            return ResponseEntity.ok("Removed");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found");
    }
}
