package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.entities.UserEntity;

import java.util.List;


public class UserRoleDto extends BaseDto {

    private final List<UserEntity> users;
    private final List<RoleEntity> roles;

    public UserRoleDto(List<UserEntity> users, List<RoleEntity> roles) {
        this.users = users;
        this.roles = roles;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }
}