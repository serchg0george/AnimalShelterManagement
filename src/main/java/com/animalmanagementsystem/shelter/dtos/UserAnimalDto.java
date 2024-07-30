package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.List;

public class UserAnimalDto extends BaseDto {
    private final List<UserDto> users;
    private final List<AnimalDto> animals;

    public UserAnimalDto(List<UserDto> users, List<AnimalDto> animals) {
        this.users = users;
        this.animals = animals;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public List<AnimalDto> getAnimals() {
        return animals;
    }
}
