package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;


public class UserRoleDto extends BaseDto {

    private final UserDto user;
    private final RoleDto role;

    public UserRoleDto(UserDto user, RoleDto role) {
        this.user = user;
        this.role = role;
    }

    public UserDto getUsers() {
        return user;
    }

    public RoleDto getRoles() {
        return role;
    }
}