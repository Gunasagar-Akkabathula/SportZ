package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.model.Product;
import com.ecommerce.Shop.model.User;
import com.ecommerce.Shop.service.ProductService;
import com.ecommerce.Shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    private boolean isAdmin(String email) {
        User user = userService.getUserByEmail(email);
        return user != null && "admin".equals(user.getRole());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Search endpoint
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.ok(productService.getAllProducts());
        }
        return ResponseEntity.ok(productService.searchProducts(query.trim()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getAllProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestParam String adminEmail, @RequestBody Product product) {
        if (!isAdmin(adminEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized");
        }
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestParam String adminEmail, @RequestBody Product product) {
        if (!isAdmin(adminEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized");
        }
        product.setId(id);
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, @RequestParam String adminEmail) {
        if (!isAdmin(adminEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorized");
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok("Deleted");
    }
}
