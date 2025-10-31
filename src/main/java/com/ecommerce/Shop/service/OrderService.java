package com.ecommerce.Shop.service;

import com.ecommerce.Shop.model.Order;
import com.ecommerce.Shop.model.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByUserEmail(String email) {
        return orderRepository.findByUserEmailOrderByCreatedAtDesc(email);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderByRazorpayOrderId(String razorpayOrderId) {
        return orderRepository.findByRazorpayOrderId(razorpayOrderId);
    }
}
