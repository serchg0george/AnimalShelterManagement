package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.*;
import com.animalmanagementsystem.shelter.entities.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AnimalMapper {

    public AnimalDto mapEntityToDto(AnimalEntity entity) {
        return mapEntityToDto(entity, new HashSet<>());
    }

    private AnimalDto mapEntityToDto(AnimalEntity entity, Set<Long> mappedAnimals) {
        if (entity == null || mappedAnimals.contains(entity.getId())) {
            return null;
        }

        mappedAnimals.add(entity.getId());
        AnimalDto animalDto = new AnimalDto();

        animalDto.setId(entity.getId());
        animalDto.setName(entity.getName());
        animalDto.setSpecies(entity.getSpecies());
        animalDto.setAge(entity.getAge());
        animalDto.setCage(cageEntityToCageDto(entity.getCage()));
        animalDto.setAnimals(userAnimalEntityListToUserAnimalDtoList(entity.getAnimals(), mappedAnimals));
        animalDto.setHealth(healthEntityToHealthDto(entity.getHealth()));

        return animalDto;
    }

    public AnimalEntity mapDtoToEntity(AnimalDto dto) {
        return mapDtoToEntity(dto, new HashSet<>());
    }

    private AnimalEntity mapDtoToEntity(AnimalDto dto, Set<Long> mappedAnimals) {
        if (dto == null || mappedAnimals.contains(dto.getId())) {
            return null;
        }

        mappedAnimals.add(dto.getId());
        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(dto.getId());
        animalEntity.setName(dto.getName());
        animalEntity.setSpecies(dto.getSpecies());
        animalEntity.setAge(dto.getAge());
        animalEntity.setCage(cageDtoToCageEntity(dto.getCage()));
        animalEntity.setAnimals(userAnimalDtoListToUserAnimalEntityList(dto.getAnimals(), mappedAnimals));
        animalEntity.setHealth(healthDtoToHealthEntity(dto.getHealth()));

        return animalEntity;
    }

    protected CageDto cageEntityToCageDto(CageEntity cageEntity) {
        if (cageEntity == null) {
            return null;
        }

        CageDto cageDto = new CageDto();

        cageDto.setId(cageEntity.getId());
        cageDto.setCageNumber(cageEntity.getCageNumber());
        cageDto.setAvailability(cageEntity.getAvailability());

        return cageDto;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPhoneNumber(userEntity.getPhoneNumber());

        return userDto;
    }

    protected UserAnimalDto userAnimalEntityToUserAnimalDto(UserAnimalEntity userAnimalEntity, Set<Long> mappedAnimals) {
        if (userAnimalEntity == null) {
            return null;
        }

        UserDto users = userEntityToUserDto(userAnimalEntity.getUsers());
        AnimalDto animals = mapEntityToDto(userAnimalEntity.getAnimals(), mappedAnimals);

        UserAnimalDto userAnimalDto = new UserAnimalDto(users, animals);

        userAnimalDto.setId(userAnimalEntity.getId());

        return userAnimalDto;
    }

    protected List<UserAnimalDto> userAnimalEntityListToUserAnimalDtoList(List<UserAnimalEntity> list, Set<Long> mappedAnimals) {
        if (list == null) {
            return List.of();
        }

        List<UserAnimalDto> list1 = new ArrayList<>(list.size());
        for (UserAnimalEntity userAnimalEntity : list) {
            list1.add(userAnimalEntityToUserAnimalDto(userAnimalEntity, mappedAnimals));
        }

        return list1;
    }

    protected HealthDto healthEntityToHealthDto(HealthEntity healthEntity) {
        if (healthEntity == null) {
            return null;
        }

        HealthDto healthDto = new HealthDto();

        healthDto.setId(healthEntity.getId());
        healthDto.setStatus(healthEntity.getStatus());
        healthDto.setUpdateDate(healthEntity.getUpdateDate());

        return healthDto;
    }

    protected CageEntity cageDtoToCageEntity(CageDto cageDto) {
        if (cageDto == null) {
            return null;
        }

        CageEntity cageEntity = new CageEntity();

        cageEntity.setId(cageDto.getId());
        cageEntity.setCageNumber(cageDto.getCageNumber());
        cageEntity.setAvailability(cageDto.getAvailability());

        return cageEntity;
    }

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());

        return userEntity;
    }

    protected UserAnimalEntity userAnimalDtoToUserAnimalEntity(UserAnimalDto userAnimalDto, Set<Long> mappedAnimals) {
        if (userAnimalDto == null) {
            return null;
        }

        UserAnimalEntity userAnimalEntity = new UserAnimalEntity();

        userAnimalEntity.setId(userAnimalDto.getId());
        userAnimalEntity.setUsers(userDtoToUserEntity(userAnimalDto.getUsers()));
        userAnimalEntity.setAnimals(mapDtoToEntity(userAnimalDto.getAnimals(), mappedAnimals));

        return userAnimalEntity;
    }

    protected List<UserAnimalEntity> userAnimalDtoListToUserAnimalEntityList(List<UserAnimalDto> list, Set<Long> mappedAnimals) {
        if (list == null) {
            return List.of();
        }

        List<UserAnimalEntity> list1 = new ArrayList<>(list.size());
        for (UserAnimalDto userAnimalDto : list) {
            list1.add(userAnimalDtoToUserAnimalEntity(userAnimalDto, mappedAnimals));
        }

        return list1;
    }

    protected HealthEntity healthDtoToHealthEntity(HealthDto healthDto) {
        if (healthDto == null) {
            return null;
        }

        HealthEntity healthEntity = new HealthEntity();

        healthEntity.setId(healthDto.getId());
        healthEntity.setStatus(healthDto.getStatus());
        healthEntity.setUpdateDate(healthDto.getUpdateDate());

        return healthEntity;
    }
}
