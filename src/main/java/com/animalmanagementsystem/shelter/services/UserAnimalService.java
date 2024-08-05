package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;

import java.util.List;

public interface UserAnimalService {
    UserAnimalDto createUserAnimal(UserAnimalDto userAnimalDto);

    UserAnimalDto getUserAnimalById(Long id);

    List<UserAnimalDto> getAllUserAnimals();

    UserAnimalDto updateUserAnimal(UserAnimalDto userAnimalDto, Long id);

    void deleteUserAnimal(Long id);
}
