package com.ecommerce.Shop.service;

import com.ecommerce.Shop.model.Product;
import com.ecommerce.Shop.model.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    // New method to get products by category ID
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }
}
