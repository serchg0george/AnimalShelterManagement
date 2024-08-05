package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;


public class UserRoleDto extends BaseDto {

    private final UserDto users;
    private final RoleDto roles;

    public UserRoleDto(UserDto users, RoleDto roles) {
        this.users = users;
        this.roles = roles;
    }

    public UserDto getUsers() {
        return users;
    }

    public RoleDto getRoles() {
        return roles;
    }
}