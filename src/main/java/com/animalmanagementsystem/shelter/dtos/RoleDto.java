package com.animalmanagementsystem.shelter.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleDto(Long id,
                      @NotBlank @Size(max = 150) String name,
                      @NotBlank @Size(max = 200) String description) {
}
