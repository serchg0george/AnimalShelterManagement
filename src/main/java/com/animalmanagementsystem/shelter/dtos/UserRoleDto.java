package com.animalmanagementsystem.shelter.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRoleDto(Long id,
                          @NotNull @NotBlank UserDto users,
                          @NotNull @NotBlank RoleDto roles) {
}
