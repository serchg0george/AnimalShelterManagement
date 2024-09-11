package com.animalmanagementsystem.shelter.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record HealthDto(Long id,
                        @NotBlank @Size(max = 50) String status,
                        Date updateDate) {
}