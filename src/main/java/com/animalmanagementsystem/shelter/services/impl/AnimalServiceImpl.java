package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.mappers.AnimalMapper;
import com.animalmanagementsystem.shelter.repository.AnimalRepository;
import com.animalmanagementsystem.shelter.services.AnimalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private static final String ANIMAL_NOT_FOUND_MESSAGE = "Animal not found";

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    public AnimalDto createAnimal(AnimalDto animalDto) {
        AnimalEntity animalEntity = animalMapper.mapDtoToEntity(animalDto);
        AnimalEntity savedAnimal = animalRepository.save(animalEntity);
        return animalMapper.mapEntityToDto(savedAnimal);
    }

    @Override
    public AnimalDto getAnimalById(Long id) {
        return animalRepository.findById(id)
                .map(animalMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(ANIMAL_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<AnimalDto> getAllAnimals() {
        List<AnimalEntity> animalEntities = animalRepository.findAll();
        return animalEntities.stream()
                .map(animalMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public AnimalDto updateAnimal(AnimalDto animalDto) {
        AnimalEntity animalEntity = animalMapper.mapDtoToEntity(animalDto);
        Optional<AnimalEntity> optionalAnimalEntity = animalRepository.findById(animalDto.getId());
        if (optionalAnimalEntity.isEmpty()) {
            throw new EntityNotFoundException(ANIMAL_NOT_FOUND_MESSAGE);
        }
        AnimalEntity updatedAnimalEntity = optionalAnimalEntity.get();
        updatedAnimalEntity.setName(animalEntity.getName());
        updatedAnimalEntity.setAge(animalEntity.getAge());
        updatedAnimalEntity.setSpecies(animalEntity.getSpecies());
        updatedAnimalEntity.setHealth(animalEntity.getHealth());
        updatedAnimalEntity.setCage(animalEntity.getCage());
        return animalMapper.mapEntityToDto(animalRepository.save(updatedAnimalEntity));
    }

    @Override
    public void deleteAnimal(Long id) {
        Optional<AnimalEntity> optionalAnimalEntity = animalRepository.findById(id);
        if (optionalAnimalEntity.isEmpty()) {
            throw new EntityNotFoundException(ANIMAL_NOT_FOUND_MESSAGE);
        }
        animalRepository.deleteById(id);
    }
}
