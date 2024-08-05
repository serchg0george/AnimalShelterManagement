package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.*;
import com.animalmanagementsystem.shelter.entities.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAnimalMapper {

    public UserAnimalDto mapEntityToDto(UserAnimalEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDto users = userEntityToUserDto(entity.getUsers());
        AnimalDto animals = animalEntityToAnimalDto(entity.getAnimals());

        UserAnimalDto userAnimalDto = new UserAnimalDto(users, animals);
        userAnimalDto.setId(entity.getId());

        return userAnimalDto;
    }

    public UserAnimalEntity mapDtoToEntity(UserAnimalDto dto) {
        if (dto == null) {
            return null;
        }

        UserAnimalEntity userAnimalEntity = new UserAnimalEntity();
        userAnimalEntity.setId(dto.getId());
        userAnimalEntity.setUsers(userDtoToUserEntity(dto.getUsers()));
        userAnimalEntity.setAnimals(animalDtoToAnimalEntity(dto.getAnimals()));

        return userAnimalEntity;
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

    protected List<UserAnimalEntity> userAnimalDtoListToUserAnimalEntityList(List<UserAnimalDto> list) {
        if (list == null) {
            return List.of();
        }

        List<UserAnimalEntity> list1 = new ArrayList<>(list.size());
        for (UserAnimalDto userAnimalDto : list) {
            list1.add(mapDtoToEntity(userAnimalDto));
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

    protected AnimalDto animalEntityToAnimalDto(AnimalEntity animalEntity) {
        if (animalEntity == null) {
            return null;
        }

        AnimalDto animalDto = new AnimalDto();
        animalDto.setId(animalEntity.getId());
        animalDto.setName(animalEntity.getName());
        animalDto.setSpecies(animalEntity.getSpecies());
        animalDto.setAge(animalEntity.getAge());
        animalDto.setCage(cageEntityToCageDto(animalEntity.getCage()));
        animalDto.setHealth(healthEntityToHealthDto(animalEntity.getHealth()));

        return animalDto;
    }

    protected AnimalEntity animalDtoToAnimalEntity(AnimalDto animalDto) {
        if (animalDto == null) {
            return null;
        }

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setId(animalDto.getId());
        animalEntity.setName(animalDto.getName());
        animalEntity.setSpecies(animalDto.getSpecies());
        animalEntity.setAge(animalDto.getAge());
        animalEntity.setCage(cageDtoToCageEntity(animalDto.getCage()));
        animalEntity.setHealth(healthDtoToHealthEntity(animalDto.getHealth()));

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
}
