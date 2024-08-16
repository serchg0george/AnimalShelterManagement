package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;

public interface HealthMapper {
    HealthDto mapEntityToDto(HealthEntity healthEntity);

    HealthEntity mapDtoToEntity(HealthDto healthDto);
}
