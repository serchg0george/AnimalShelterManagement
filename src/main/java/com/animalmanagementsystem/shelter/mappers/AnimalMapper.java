package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;

public interface AnimalMapper {
    AnimalDto mapEntityToDto(AnimalEntity animalEntity);

    AnimalEntity mapDtoToEntity(AnimalDto animalDto);
}
