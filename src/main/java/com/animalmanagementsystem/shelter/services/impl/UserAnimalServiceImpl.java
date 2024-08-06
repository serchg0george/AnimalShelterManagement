package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;
import com.animalmanagementsystem.shelter.entities.UserAnimalEntity;
import com.animalmanagementsystem.shelter.mappers.UserAnimalMapper;
import com.animalmanagementsystem.shelter.repository.UserAnimalRepository;
import com.animalmanagementsystem.shelter.services.UserAnimalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnimalServiceImpl implements UserAnimalService {

    private final UserAnimalRepository userAnimalRepository;
    private final UserAnimalMapper userAnimalMapper;
    private static final String USER_ANIMAL_NOT_FOUND = "User animal not found";

    public UserAnimalServiceImpl(UserAnimalRepository userAnimalRepository, UserAnimalMapper userAnimalMapper) {
        this.userAnimalRepository = userAnimalRepository;
        this.userAnimalMapper = userAnimalMapper;
    }

    @Override
    public UserAnimalDto createUserAnimal(UserAnimalDto userAnimalDto) {
        UserAnimalEntity userAnimalEntity = userAnimalMapper.mapDtoToEntity(userAnimalDto);
        UserAnimalEntity savedUserAnimal = userAnimalRepository.save(userAnimalEntity);
        return userAnimalMapper.mapEntityToDto(savedUserAnimal);
    }

    @Override
    public UserAnimalDto getUserAnimalById(Long id) {
        return userAnimalRepository.findById(id)
                .map(userAnimalMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(USER_ANIMAL_NOT_FOUND));
    }

    @Override
    public List<UserAnimalDto> getAllUserAnimals() {
        List<UserAnimalEntity> userAnimalEntities = userAnimalRepository.findAll();
        return userAnimalEntities.stream()
                .map(userAnimalMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public UserAnimalDto updateUserAnimal(UserAnimalDto userAnimalDto, Long id) {
        UserAnimalEntity userAnimalEntity = userAnimalMapper.mapDtoToEntity(userAnimalDto);
        Optional<UserAnimalEntity> optionalUserAnimalEntity = userAnimalRepository.findById(userAnimalDto.id());
        if (optionalUserAnimalEntity.isEmpty()) {
            throw new EntityNotFoundException(USER_ANIMAL_NOT_FOUND);
        }

        UserAnimalEntity updatedUserAnimalEntity = optionalUserAnimalEntity.get();
        updatedUserAnimalEntity.setUsers(userAnimalEntity.getUsers());
        updatedUserAnimalEntity.setAnimals(userAnimalEntity.getAnimals());
        updatedUserAnimalEntity.setId(id);
        return userAnimalMapper.mapEntityToDto(userAnimalRepository.save(updatedUserAnimalEntity));
    }

    @Override
    public void deleteUserAnimal(Long id) {
        Optional<UserAnimalEntity> optionalUserAnimalEntity = userAnimalRepository.findById(id);
        if (optionalUserAnimalEntity.isEmpty()) {
            throw new EntityNotFoundException(USER_ANIMAL_NOT_FOUND);
        }
        userAnimalRepository.deleteById(id);
    }
}
