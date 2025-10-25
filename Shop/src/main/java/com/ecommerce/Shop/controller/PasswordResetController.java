package com.ecommerce.Shop.controller;

import com.ecommerce.Shop.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/password-reset")
@CrossOrigin(origins = "*")
public class PasswordResetController {
    
    @Autowired
    private PasswordResetService passwordResetService;
    
    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, Object>> sendOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        
        boolean sent = passwordResetService.generateAndSendOTP(email);
        
        Map<String, Object> response = new HashMap<>();
        if (sent) {
            response.put("success", true);
            response.put("message", "OTP sent to your email");
        } else {
            response.put("success", false);
            response.put("message", "Email not found");
        }
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, Object>> verifyOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        
        boolean valid = passwordResetService.verifyOTP(email, otp);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", valid);
        response.put("message", valid ? "OTP verified" : "Invalid or expired OTP");
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/reset")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");
        
        boolean reset = passwordResetService.resetPassword(email, otp, newPassword);
        
        Map<String, Object> response = new HashMap<>();
        if (reset) {
            response.put("success", true);
            response.put("message", "Password reset successfully");
        } else {
            response.put("success", false);
            response.put("message", "Failed to reset password");
        }
        
        return ResponseEntity.ok(response);
    }
}

