package com.animalmanagementsystem.shelter.authorization;

import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.repositories.RoleRepository;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final RoleRepository roleRepository;
    UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(username);
        Optional<RoleEntity> role = roleRepository.findById(1L);
        if (user.isPresent() && role.isPresent()) {
            var userObj = user.get();
            var roleObj = role.get();
            return User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(getRole(roleObj))
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username: " + username);
        }
    }


    private String getRole(RoleEntity role) {
        return role.getName();
    }
}
