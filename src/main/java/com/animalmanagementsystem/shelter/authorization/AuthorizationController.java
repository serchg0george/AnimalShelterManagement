package com.animalmanagementsystem.shelter.authorization;

import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/login")
public class AuthorizationController {

    private final UserRepository userRepository;

    public AuthorizationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }
}
