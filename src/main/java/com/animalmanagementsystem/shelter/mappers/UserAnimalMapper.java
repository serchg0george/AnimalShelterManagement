package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;
import com.animalmanagementsystem.shelter.entities.UserAnimalEntity;

public interface UserAnimalMapper {
    UserAnimalDto mapEntityToDto(UserAnimalEntity userAnimalEntity);

    UserAnimalEntity mapDtoToEntity(UserAnimalDto userAnimalDto);
}
