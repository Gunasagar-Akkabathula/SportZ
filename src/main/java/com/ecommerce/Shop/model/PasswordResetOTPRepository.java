package com.ecommerce.Shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PasswordResetOTPRepository extends JpaRepository<PasswordResetOTP, Long> {
    
    Optional<PasswordResetOTP> findByEmailAndUsedFalse(String email);
    
    void deleteByEmail(String email);
}

