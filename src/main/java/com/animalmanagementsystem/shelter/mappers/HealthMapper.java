package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HealthMapper extends BaseMapper<HealthEntity, HealthDto> {
    @Override
    HealthDto mapEntityToDto(HealthEntity entity);
    @Override
    HealthEntity mapDtoToEntity(HealthDto dto);
}
