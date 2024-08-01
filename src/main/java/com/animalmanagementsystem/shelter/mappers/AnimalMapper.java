package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.*;
import com.animalmanagementsystem.shelter.entities.*;

import java.util.ArrayList;
import java.util.List;

public class AnimalMapper {

    public AnimalDto mapEntityToDto(AnimalEntity entity) {
        if (entity == null) {
            return null;
        }

        AnimalDto animalDto = new AnimalDto();

        animalDto.setId(entity.getId());
        animalDto.setName(entity.getName());
        animalDto.setSpecies(entity.getSpecies());
        animalDto.setAge(entity.getAge());
        animalDto.setCage(cageEntityToCageDto(entity.getCage()));
        animalDto.setAnimals(userAnimalEntityListToUserAnimalDtoList(entity.getAnimals()));
        animalDto.setHealth(healthEntityToHealthDto(entity.getHealth()));

        return animalDto;
    }

    public AnimalEntity mapDtoToEntity(AnimalDto dto) {
        if (dto == null) {
            return null;
        }

        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(dto.getId());
        animalEntity.setName(dto.getName());
        animalEntity.setSpecies(dto.getSpecies());
        animalEntity.setAge(dto.getAge());
        animalEntity.setCage(cageDtoToCageEntity(dto.getCage()));
        animalEntity.setAnimals(userAnimalDtoListToUserAnimalEntityList(dto.getAnimals()));
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

    protected UserAnimalDto userAnimalEntityToUserAnimalDto(UserAnimalEntity userAnimalEntity) {
        if (userAnimalEntity == null) {
            return null;
        }

        UserDto user = null;
        AnimalDto animal = null;

        UserAnimalDto userAnimalDto = new UserAnimalDto(user, animal);

        userAnimalDto.setId(userAnimalEntity.getId());

        return userAnimalDto;
    }

    protected List<UserAnimalDto> userAnimalEntityListToUserAnimalDtoList(List<UserAnimalEntity> list) {
        if (list == null) {
            return null;
        }

        List<UserAnimalDto> list1 = new ArrayList<>(list.size());
        for (UserAnimalEntity userAnimalEntity : list) {
            list1.add(userAnimalEntityToUserAnimalDto(userAnimalEntity));
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

    protected UserAnimalEntity userAnimalDtoToUserAnimalEntity(UserAnimalDto userAnimalDto) {
        if (userAnimalDto == null) {
            return null;
        }

        UserAnimalEntity userAnimalEntity = new UserAnimalEntity();

        userAnimalEntity.setId(userAnimalDto.getId());
        userAnimalEntity.setUsers(userDtoToUserEntity(userAnimalDto.getUsers()));
        userAnimalEntity.setAnimals(mapDtoToEntity(userAnimalDto.getAnimals()));

        return userAnimalEntity;
    }

    protected List<UserAnimalEntity> userAnimalDtoListToUserAnimalEntityList(List<UserAnimalDto> list) {
        if (list == null) {
            return null;
        }

        List<UserAnimalEntity> list1 = new ArrayList<>(list.size());
        for (UserAnimalDto userAnimalDto : list) {
            list1.add(userAnimalDtoToUserAnimalEntity(userAnimalDto));
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
