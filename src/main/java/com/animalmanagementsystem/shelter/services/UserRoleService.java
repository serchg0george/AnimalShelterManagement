package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.UserRoleDto;

import java.util.List;

public interface UserRoleService {
    UserRoleDto createUserRole(UserRoleDto userRoleDto);

    UserRoleDto getUserRoleById(Long id);

    List<UserRoleDto> getAllUserRoles();

    UserRoleDto updateUserRole(UserRoleDto userRoleDto, Long id);

    void deleteUserRole(Long id);
}
