package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.List;


public class UserRoleDto extends BaseDto {

    private final List<UserDto> users;
    private final List<RoleDto> roles;

    public UserRoleDto(List<UserDto> users, List<RoleDto> roles) {
        this.users = users;
        this.roles = roles;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }
}