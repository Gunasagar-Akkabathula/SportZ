package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.model.Category;
import com.ecommerce.Shop.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        updatedCategory.setId(id);
        return categoryRepository.save(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
