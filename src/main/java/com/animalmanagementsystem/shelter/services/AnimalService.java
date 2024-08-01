package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;

import java.util.List;

public interface AnimalService {
    AnimalDto createAnimal(AnimalDto animalDto);
    AnimalDto getAnimalById(Long id);
    List<AnimalDto> getAllAnimals();
    AnimalDto updateAnimal(AnimalDto animalDto);
    void deleteAnimal(Long id);
}
