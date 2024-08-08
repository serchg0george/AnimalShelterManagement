package com.animalmanagementsystem.shelter.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(Long id,
                      @NotBlank @Email String email,
                      @NotBlank String password,
                      @NotBlank String firstName,
                      @NotBlank String lastName,
                      @NotBlank String phoneNumber) {
}