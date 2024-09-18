package com.animalmanagementsystem.shelter.auth;

import com.animalmanagementsystem.shelter.auth.config.JwtService;
import com.animalmanagementsystem.shelter.entities.Role;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new UserEntity(
                request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                Role.USER);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
