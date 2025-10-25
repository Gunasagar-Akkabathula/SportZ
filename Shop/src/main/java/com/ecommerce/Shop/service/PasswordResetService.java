package com.ecommerce.Shop.service;

import com.ecommerce.Shop.model.PasswordResetOTP;
import com.ecommerce.Shop.model.PasswordResetOTPRepository;
import com.ecommerce.Shop.model.User;
import com.ecommerce.Shop.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetService {
    
    @Autowired
    private PasswordResetOTPRepository otpRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    // ✅ ADD THIS: Password encoder
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Transactional
    public boolean generateAndSendOTP(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }
        
        otpRepository.deleteByEmail(email);
        
        String otp = String.format("%06d", new Random().nextInt(999999));
        
        PasswordResetOTP resetOTP = new PasswordResetOTP(email, otp);
        otpRepository.save(resetOTP);
        
        try {
            emailService.sendOTPEmail(email, otp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verifyOTP(String email, String otp) {
        Optional<PasswordResetOTP> resetOTP = otpRepository.findByEmailAndUsedFalse(email);
        
        if (resetOTP.isEmpty()) {
            return false;
        }
        
        PasswordResetOTP otpEntity = resetOTP.get();
        
        if (otpEntity.getOtp().equals(otp) && 
            LocalDateTime.now().isBefore(otpEntity.getExpiresAt())) {
            return true;
        }
        
        return false;
    }
    
    @Transactional
    public boolean resetPassword(String email, String otp, String newPassword) {
        if (!verifyOTP(email, otp)) {
            return false;
        }
        
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
        
        // ✅ ENCRYPT PASSWORD BEFORE SAVING
        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        
        Optional<PasswordResetOTP> resetOTP = otpRepository.findByEmailAndUsedFalse(email);
        if (resetOTP.isPresent()) {
            PasswordResetOTP otpEntity = resetOTP.get();
            otpEntity.setUsed(true);
            otpRepository.save(otpEntity);
        }
        
        return true;
    }
}
