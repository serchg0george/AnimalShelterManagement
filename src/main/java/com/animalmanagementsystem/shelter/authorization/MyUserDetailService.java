package com.animalmanagementsystem.shelter.authorization;

import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(getRole(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username: " + username);
        }
    }

    private String getRole(UserEntity user) {
        return user.getUserRoleEntity().stream()
                .map(userRole -> userRole.getRoles().getName())
                .findFirst()
                .orElse("USER");
    }
}
