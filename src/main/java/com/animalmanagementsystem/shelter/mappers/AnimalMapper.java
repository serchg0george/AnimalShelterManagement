package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.entities.HealthEntity;

import java.util.Date;
import java.util.List;

public class AnimalMapper {

    public AnimalDto mapEntityToDto(AnimalEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        String name = null;
        String species = null;
        Integer age = null;
        CageDto cage = null;
        HealthDto health = null;

        id = entity.getId();
        name = entity.getName();
        species = entity.getSpecies();
        age = entity.getAge();
        cage = cageEntityToCageDto(entity.getCage());
        health = healthEntityToHealthDto(entity.getHealth());

        List<UserAnimalDto> users = null;

        return new AnimalDto(id, name, species, age, cage, users, health);
    }

    public AnimalEntity mapDtoToEntity(AnimalDto dto) {
        if (dto == null) {
            return null;
        }

        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(dto.id());
        animalEntity.setName(dto.name());
        animalEntity.setSpecies(dto.species());
        animalEntity.setAge(dto.age());
        animalEntity.setCage(cageDtoToCageEntity(dto.cage()));
        animalEntity.setHealth(healthDtoToHealthEntity(dto.health()));

        return animalEntity;
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

        Date updateDate = null;

        return new HealthDto(id, status, updateDate);
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
}
