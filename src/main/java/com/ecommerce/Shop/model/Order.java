package com.ecommerce.Shop.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private String userName;
    private String userPhone;
    private String shippingAddress;
    
    @Column(name = "total_amount")
    private Double totalAmount;
    
    @Column(name = "payment_method")
    private String paymentMethod; // "UPI" or "COD"
    
    @Column(name = "payment_status")
    private String paymentStatus; // "PENDING", "PAID", "FAILED"
    
    @Column(name = "order_status")
    private String orderStatus; // "PLACED", "SHIPPED", "DELIVERED", "CANCELLED"
    
    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;
    
    @Column(name = "razorpay_payment_id")
    private String razorpayPaymentId;
    
    @Column(name = "cod_charges")
    private Double codCharges;
    
    @Column(name = "order_items", length = 2000)
    private String orderItems; // JSON string of cart items
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Order() {
        this.createdAt = LocalDateTime.now();
        this.orderStatus = "PLACED";
        this.paymentStatus = "PENDING";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }
    
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    
    public String getRazorpayOrderId() { return razorpayOrderId; }
    public void setRazorpayOrderId(String razorpayOrderId) { this.razorpayOrderId = razorpayOrderId; }
    
    public String getRazorpayPaymentId() { return razorpayPaymentId; }
    public void setRazorpayPaymentId(String razorpayPaymentId) { this.razorpayPaymentId = razorpayPaymentId; }
    
    public Double getCodCharges() { return codCharges; }
    public void setCodCharges(Double codCharges) { this.codCharges = codCharges; }
    
    public String getOrderItems() { return orderItems; }
    public void setOrderItems(String orderItems) { this.orderItems = orderItems; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
