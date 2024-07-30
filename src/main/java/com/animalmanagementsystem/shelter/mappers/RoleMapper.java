package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper extends BaseMapper<RoleEntity, RoleDto> {
    @Override
    RoleDto mapEntityToDto(RoleEntity entity);
    @Override
    RoleEntity mapDtoToEntity(RoleDto dto);
}
