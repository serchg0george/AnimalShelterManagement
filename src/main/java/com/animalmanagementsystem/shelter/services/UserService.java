package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}
