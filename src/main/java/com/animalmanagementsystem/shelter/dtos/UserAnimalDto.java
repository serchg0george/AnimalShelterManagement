package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class UserAnimalDto extends BaseDto {
    private final UserDto user;
    private final AnimalDto animal;

    public UserAnimalDto(UserDto user, AnimalDto animal) {
        this.user = user;
        this.animal = animal;
    }

    public UserDto getUsers() {
        return user;
    }

    public AnimalDto getAnimals() {
        return animal;
    }
}
