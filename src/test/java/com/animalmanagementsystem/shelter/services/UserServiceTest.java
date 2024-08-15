package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import com.animalmanagementsystem.shelter.services.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUserTest() {
        UserDto userDto = new UserDto(null, "john.doe@example.com", "password123", "John", "Doe", "1234567890");

        UserDto savedUserDto = userService.createUser(userDto);

        assertThat(savedUserDto).isNotNull();
        assertThat(savedUserDto.firstName()).isEqualTo("John");
        assertThat(savedUserDto.lastName()).isEqualTo("Doe");
        assertThat(savedUserDto.email()).isEqualTo("john.doe@example.com");
    }

    @Test
    void getUserByIdTest() {
        UserEntity userEntity = new UserEntity("jane.doe@example.com", "password123", "Jane", "Doe", "0987654321");
        userRepository.save(userEntity);

        UserDto foundUserDto = userService.getUserById(userEntity.getId());

        assertThat(foundUserDto).isNotNull();
        assertThat(foundUserDto.firstName()).isEqualTo("Jane");
        assertThat(foundUserDto.lastName()).isEqualTo("Doe");
        assertThat(foundUserDto.email()).isEqualTo("jane.doe@example.com");
    }

    @Test
    void getAllUsersTest() {
        UserEntity userEntity1 = new UserEntity("john.smith@example.com", "password1", "John", "Smith", "1112223333");
        UserEntity userEntity2 = new UserEntity("alice.johnson@example.com", "password2", "Alice", "Johnson", "4445556666");

        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        List<UserDto> allUsers = userService.getAllUsers();

        assertThat(allUsers)
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(2)
                .extracting("email").contains("john.smith@example.com", "alice.johnson@example.com");
    }

    @Test
    void updateUserTest() {
        UserEntity userEntity = new UserEntity("mike.brown@example.com", "initialPass", "Mike", "Brown", "7778889999");
        userRepository.save(userEntity);

        UserDto updateUserDto = new UserDto(userEntity.getId(), "mike.brown@example.com", "newPass123", "Mike", "Brown", "7778889999");

        UserDto updatedUserDto = userService.updateUser(updateUserDto, userEntity.getId());

        assertThat(updatedUserDto).isNotNull();
        assertThat(updatedUserDto.firstName()).isEqualTo("Mike");
        assertThat(updatedUserDto.password()).isEqualTo("newPass123");
    }

    @Test
    void deleteUserTest() {
        UserEntity userEntity = new UserEntity("Tom", "White", "tom.white@example.com", "securePass", "1231231234");
        userRepository.save(userEntity);

        userService.deleteUser(userEntity.getId());

        Optional<UserEntity> deletedUser = userRepository.findById(userEntity.getId());
        assertThat(deletedUser).isEmpty();
    }

    @Test
    void getUserById_NotFoundTest() {
        Long nonExistentId = 999L;
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(nonExistentId);
        });

        assertThat(exception.getMessage()).isEqualTo("User not found");
    }
}

