package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.searchs.UserSearchRequest;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers(int pageNo, int pageSize);

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);

    List<UserDto> findUserByCriteria(UserSearchRequest request);
}
