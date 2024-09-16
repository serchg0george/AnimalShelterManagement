package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;
import com.animalmanagementsystem.shelter.entities.UserAnimalEntity;
import com.animalmanagementsystem.shelter.exceptions.UserAnimalNotFoundException;
import com.animalmanagementsystem.shelter.mappers.UserAnimalMapper;
import com.animalmanagementsystem.shelter.repositories.UserAnimalRepository;
import com.animalmanagementsystem.shelter.services.UserAnimalService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnimalServiceImpl implements UserAnimalService {

    private final UserAnimalRepository userAnimalRepository;
    private final UserAnimalMapper userAnimalMapper;

    public UserAnimalServiceImpl(UserAnimalRepository userAnimalRepository, UserAnimalMapper userAnimalMapper) {
        this.userAnimalRepository = userAnimalRepository;
        this.userAnimalMapper = userAnimalMapper;
    }

    @Override
    @Transactional
    public UserAnimalDto createUserAnimal(UserAnimalDto userAnimalDto) {
        UserAnimalEntity userAnimalEntity = userAnimalMapper.mapDtoToEntity(userAnimalDto);
        UserAnimalEntity savedUserAnimal = userAnimalRepository.save(userAnimalEntity);
        return userAnimalMapper.mapEntityToDto(savedUserAnimal);
    }

    @Override
    public UserAnimalDto getUserAnimalById(Long id) {
        return userAnimalRepository.findById(id)
                .map(userAnimalMapper::mapEntityToDto)
                .orElseThrow(() -> new UserAnimalNotFoundException(id));
    }

    @Override
    public List<UserAnimalDto> getAllUserAnimals() {
        List<UserAnimalEntity> userAnimalEntities = userAnimalRepository.findAll();
        return userAnimalEntities.stream()
                .map(userAnimalMapper::mapEntityToDto)
                .toList();
    }

    @Override
    @Transactional
    public UserAnimalDto updateUserAnimal(UserAnimalDto userAnimalDto, Long id) {
        UserAnimalEntity userAnimalEntity = userAnimalMapper.mapDtoToEntity(userAnimalDto);
        Optional<UserAnimalEntity> optionalUserAnimalEntity = userAnimalRepository.findById(userAnimalDto.id());
        if (optionalUserAnimalEntity.isEmpty()) {
            throw new UserAnimalNotFoundException(id);
        }

        UserAnimalEntity updatedUserAnimalEntity = optionalUserAnimalEntity.get();
        updatedUserAnimalEntity.setUsers(userAnimalEntity.getUsers());
        updatedUserAnimalEntity.setAnimals(userAnimalEntity.getAnimals());
        updatedUserAnimalEntity.setId(id);
        return userAnimalMapper.mapEntityToDto(userAnimalRepository.save(updatedUserAnimalEntity));
    }

    @Override
    @Transactional
    public void deleteUserAnimal(Long id) {
        Optional<UserAnimalEntity> optionalUserAnimalEntity = userAnimalRepository.findById(id);
        if (optionalUserAnimalEntity.isEmpty()) {
            throw new UserAnimalNotFoundException(id);
        }
        userAnimalRepository.deleteById(id);
    }
}
