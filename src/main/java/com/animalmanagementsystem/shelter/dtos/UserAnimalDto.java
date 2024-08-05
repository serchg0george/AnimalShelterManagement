package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class UserAnimalDto extends BaseDto {
    private final UserDto users;
    private final AnimalDto animals;


    public UserAnimalDto(UserDto users, AnimalDto animals) {
        this.users = users;
        this.animals = animals;
    }

    public UserDto getUsers() {
        return users;
    }

    public AnimalDto getAnimals() {
        return animals;
    }
}
