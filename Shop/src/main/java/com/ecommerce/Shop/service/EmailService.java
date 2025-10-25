package com.ecommerce.Shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendOTPEmail(String toEmail, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@yoursportsstore.com");
            message.setTo(toEmail);
            message.setSubject("Password Reset OTP - Sports Store");
            message.setText("Your OTP for password reset is: " + otp + 
                          "\n\nThis OTP is valid for 10 minutes." +
                          "\n\nIf you didn't request this, please ignore this email.");
            
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}

