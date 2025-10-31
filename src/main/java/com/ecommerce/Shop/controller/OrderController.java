package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.model.Order;
import com.ecommerce.Shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private static final Double COD_CHARGES = 40.0;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create order for COD
    @PostMapping("/create-cod")
    public ResponseEntity<?> createCodOrder(@RequestBody Order order) {
        try {
            order.setPaymentMethod("COD");
            order.setPaymentStatus("PENDING");
            order.setCodCharges(COD_CHARGES);
            
            // Add COD charges to total
            Double finalAmount = order.getTotalAmount() + COD_CHARGES;
            order.setTotalAmount(finalAmount);
            
            Order savedOrder = orderService.createOrder(order);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating COD order: " + e.getMessage());
        }
    }

    // Create mock UPI order (simulated payment)
    @PostMapping("/create-upi")
    public ResponseEntity<?> createUpiOrder(@RequestBody Order order) {
        try {
            // Generate mock payment ID
            String mockPaymentId = "MOCK_PAY_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            String mockOrderId = "MOCK_ORD_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            
            order.setPaymentMethod("UPI");
            order.setPaymentStatus("PENDING");
            order.setRazorpayOrderId(mockOrderId);
            order.setCodCharges(0.0);

            Order savedOrder = orderService.createOrder(order);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", savedOrder.getId());
            response.put("mockPaymentId", mockPaymentId);
            response.put("mockOrderId", mockOrderId);
            response.put("amount", order.getTotalAmount());
            response.put("currency", "INR");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating UPI order: " + e.getMessage());
        }
    }

    // Simulate payment verification (always succeeds for demo)
    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> paymentData) {
        try {
            String mockOrderId = paymentData.get("mockOrderId");
            String mockPaymentId = paymentData.get("mockPaymentId");

            Order order = orderService.getOrderByRazorpayOrderId(mockOrderId);
            if (order != null) {
                // Simulate payment success
                order.setPaymentStatus("PAID");
                order.setRazorpayPaymentId(mockPaymentId);
                orderService.updateOrder(order);
                return ResponseEntity.ok(Map.of("success", true, "orderId", order.getId()));
            }
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Order not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    // Get user orders
    @GetMapping("/user/{email}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String email) {
        return ResponseEntity.ok(orderService.getOrdersByUserEmail(email));
    }

    // Get COD charges
    @GetMapping("/cod-charges")
    public ResponseEntity<Map<String, Double>> getCodCharges() {
        return ResponseEntity.ok(Map.of("codCharges", COD_CHARGES));
    }
}
