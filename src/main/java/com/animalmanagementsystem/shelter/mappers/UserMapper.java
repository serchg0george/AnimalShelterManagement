package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends BaseMapper<UserEntity, UserDto> {
    @Override
    UserDto mapEntityToDto(UserEntity entity);
    @Override
    UserEntity mapDtoToEntity(UserDto dto);
}
