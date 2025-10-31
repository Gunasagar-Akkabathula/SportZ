package com.ecommerce.Shop.model;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email")
    private String userEmail;
    
    @Column(name = "item_name")
    private String itemName;
    
    private Integer quantity;
    
    private Double price;  // ADDED
    
    @Column(name = "image_url")
    private String imageUrl;  // ADDED

    // Constructors
    public Cart() {}

    public Cart(String userEmail, String itemName, Integer quantity) {
        this.userEmail = userEmail;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Cart(String userEmail, String itemName, Integer quantity, Double price, String imageUrl) {
        this.userEmail = userEmail;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getUserEmail() { 
        return userEmail; 
    }
    
    public void setUserEmail(String userEmail) { 
        this.userEmail = userEmail; 
    }
    
    public String getItemName() { 
        return itemName; 
    }
    
    public void setItemName(String itemName) { 
        this.itemName = itemName; 
    }
    
    public Integer getQuantity() { 
        return quantity; 
    }
    
    public void setQuantity(Integer quantity) { 
        this.quantity = quantity; 
    }
    
    public Double getPrice() { 
        return price; 
    }
    
    public void setPrice(Double price) { 
        this.price = price; 
    }
    
    public String getImageUrl() { 
        return imageUrl; 
    }
    
    public void setImageUrl(String imageUrl) { 
        this.imageUrl = imageUrl; 
    }
}
