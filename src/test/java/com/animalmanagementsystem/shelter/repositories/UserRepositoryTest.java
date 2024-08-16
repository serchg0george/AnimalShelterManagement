package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findAllUsersTest() {

        UserEntity userEntity1 = new UserEntity("test1@example.com", "password1", "John", "Doe", "1234567890");
        userRepository.save(userEntity1);

        List<UserEntity> userList = userRepository.findAll();

        assertThat(userList)
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .hasSize(2);

    }

    @Test
    void findUserByIdTest() {
        UserEntity userEntity = new UserEntity("test@example.com", "password", "John", "Doe", "1234567890");
        userRepository.save(userEntity);
        assertThat(userRepository.findById(userEntity.getId())).isNotEmpty();
    }

    @Test
    void createUserTest() {
        UserEntity userEntity = new UserEntity("test@example.com", "password", "John", "Doe", "1234567890");
        UserEntity saved = userRepository.save(userEntity);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void deleteUserTest() {
        UserEntity userEntity = new UserEntity("test@example.com", "password", "John", "Doe", "1234567890");
        userRepository.save(userEntity);
        userRepository.delete(userEntity);
        Optional<UserEntity> entity = userRepository.findById(userEntity.getId());
        assertThat(entity).isEmpty();
    }
}
