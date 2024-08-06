package com.animalmanagementsystem.shelter.dtos;

public record UserAnimalDto(Long id,
                            UserDto users,
                            AnimalDto animals) {
}
