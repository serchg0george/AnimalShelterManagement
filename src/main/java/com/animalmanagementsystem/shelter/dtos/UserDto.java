package com.animalmanagementsystem.shelter.dtos;

public record UserDto(Long id,
                      String email,
                      String password,
                      String firstName,
                      String lastName,
                      String phoneNumber) {
}