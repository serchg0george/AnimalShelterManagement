package com.animalmanagementsystem.shelter.authorization;

import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
public class AuthorizationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        return "Login successful";
    }
}
