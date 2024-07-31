package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimalMapper extends BaseMapper<AnimalEntity, AnimalDto> {
    @Override
    AnimalDto mapEntityToDto(AnimalEntity entity);

    @Override
    AnimalEntity mapDtoToEntity(AnimalDto dto);
}
