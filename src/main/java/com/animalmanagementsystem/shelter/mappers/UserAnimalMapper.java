package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.*;
import com.animalmanagementsystem.shelter.entities.*;

import java.util.List;

public class UserAnimalMapper {

    public UserAnimalDto mapEntityToDto(UserAnimalEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        UserDto users = null;
        AnimalDto animals = null;

        id = entity.getId();
        users = userEntityToUserDto(entity.getUsers());
        animals = animalEntityToAnimalDto(entity.getAnimals());

        return new UserAnimalDto(id, users, animals);
    }

    public UserAnimalEntity mapDtoToEntity(UserAnimalDto dto) {
        if (dto == null) {
            return null;
        }

        UserAnimalEntity userAnimalEntity = new UserAnimalEntity();

        userAnimalEntity.setId(dto.id());
        userAnimalEntity.setUsers(userDtoToUserEntity(dto.users()));
        userAnimalEntity.setAnimals(animalDtoToAnimalEntity(dto.animals()));

        return userAnimalEntity;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        Long id = null;
        String email = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;

        id = userEntity.getId();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        firstName = userEntity.getFirstName();
        lastName = userEntity.getLastName();
        phoneNumber = userEntity.getPhoneNumber();

        return new UserDto(id, email, password, firstName, lastName, phoneNumber);
    }

    protected CageDto cageEntityToCageDto(CageEntity cageEntity) {
        if (cageEntity == null) {
            return null;
        }

        Long id = null;
        String cageNumber = null;
        String availability = null;

        id = cageEntity.getId();
        cageNumber = cageEntity.getCageNumber();
        availability = cageEntity.getAvailability();

        return new CageDto(id, cageNumber, availability);
    }

    protected HealthDto healthEntityToHealthDto(HealthEntity healthEntity) {
        if (healthEntity == null) {
            return null;
        }

        Long id = null;
        String status = null;

        id = healthEntity.getId();
        status = healthEntity.getStatus();

        String availability = null;

        return new HealthDto(id, status, availability);
    }

    protected AnimalDto animalEntityToAnimalDto(AnimalEntity animalEntity) {
        if (animalEntity == null) {
            return null;
        }

        Long id = null;
        String name = null;
        String species = null;
        Integer age = null;
        CageDto cage = null;
        HealthDto health = null;

        id = animalEntity.getId();
        name = animalEntity.getName();
        species = animalEntity.getSpecies();
        age = animalEntity.getAge();
        cage = cageEntityToCageDto(animalEntity.getCage());
        health = healthEntityToHealthDto(animalEntity.getHealth());

        List<UserAnimalDto> users = null;

        return new AnimalDto(id, name, species, age, cage, users, health);
    }

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.id());
        userEntity.setEmail(userDto.email());
        userEntity.setPassword(userDto.password());
        userEntity.setFirstName(userDto.firstName());
        userEntity.setLastName(userDto.lastName());
        userEntity.setPhoneNumber(userDto.phoneNumber());

        return userEntity;
    }

    protected CageEntity cageDtoToCageEntity(CageDto cageDto) {
        if (cageDto == null) {
            return null;
        }

        CageEntity cageEntity = new CageEntity();

        cageEntity.setId(cageDto.id());
        cageEntity.setCageNumber(cageDto.cageNumber());
        cageEntity.setAvailability(cageDto.availability());

        return cageEntity;
    }

    protected HealthEntity healthDtoToHealthEntity(HealthDto healthDto) {
        if (healthDto == null) {
            return null;
        }

        HealthEntity healthEntity = new HealthEntity();

        healthEntity.setId(healthDto.id());
        healthEntity.setStatus(healthDto.status());

        return healthEntity;
    }

    protected AnimalEntity animalDtoToAnimalEntity(AnimalDto animalDto) {
        if (animalDto == null) {
            return null;
        }

        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(animalDto.id());
        animalEntity.setName(animalDto.name());
        animalEntity.setSpecies(animalDto.species());
        animalEntity.setAge(animalDto.age());
        animalEntity.setCage(cageDtoToCageEntity(animalDto.cage()));
        animalEntity.setHealth(healthDtoToHealthEntity(animalDto.health()));

        return animalEntity;
    }
}
