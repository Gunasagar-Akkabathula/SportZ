package com.ecommerce.Shop.service;

import com.ecommerce.Shop.model.User;
import com.ecommerce.Shop.model.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already in use");
        }
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Name field is mapped from controller; save user directly
        return userRepository.save(user);
    }

    public User loginUser(String email, String rawPassword) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new Exception("Invalid password");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
