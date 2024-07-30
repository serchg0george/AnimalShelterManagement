package com.animalmanagementsystem.shelter.repository;

import com.animalmanagementsystem.shelter.dtos.UserAnimalDto;
import com.animalmanagementsystem.shelter.entities.UserAnimalEntity;
import com.animalmanagementsystem.shelter.mappers.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAnimalMapper extends BaseMapper<UserAnimalEntity, UserAnimalDto> {
    @Override
    UserAnimalDto mapEntityToDto(UserAnimalEntity entity);
    @Override
    UserAnimalEntity mapDtoToEntity(UserAnimalDto dto);
}