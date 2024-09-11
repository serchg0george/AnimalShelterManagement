package com.animalmanagementsystem.shelter.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AnimalDto(Long id,
                        @NotBlank @Size(max = 150) String name,
                        @NotBlank @Size(max = 50) String species,
                        @NotNull Integer age,
                        @Nullable CageDto cage,
                        @Nullable List<UserAnimalDto> users,
                        @Nullable HealthDto health) {
}
