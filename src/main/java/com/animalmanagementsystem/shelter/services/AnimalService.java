package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.searchs.AnimalSearchRequest;

import java.util.List;

public interface AnimalService {
    AnimalDto createAnimal(AnimalDto animalDto);

    AnimalDto getAnimalById(Long id);

    List<AnimalDto> getAllAnimals(int pageNo, int pageSize);

    AnimalDto updateAnimal(AnimalDto animalDto, Long id);

    void deleteAnimal(Long id);

    List<AnimalDto> findAnimalByCriteria(AnimalSearchRequest request);
}
