package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserRoleDto;
import com.animalmanagementsystem.shelter.entities.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleMapper extends BaseMapper<UserRoleEntity, UserRoleDto> {
    @Override
    UserRoleDto mapEntityToDto(UserRoleEntity entity);
    @Override
    UserRoleEntity mapDtoToEntity(UserRoleDto dto);
}
