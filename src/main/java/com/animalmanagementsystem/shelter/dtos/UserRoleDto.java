package com.animalmanagementsystem.shelter.dtos;

public record UserRoleDto(Long id,
                          UserDto users,
                          RoleDto roles) {
}
