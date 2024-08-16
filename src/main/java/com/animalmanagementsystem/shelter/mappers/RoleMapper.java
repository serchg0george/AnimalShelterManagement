package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;

public interface RoleMapper {
    RoleDto mapEntityToDto(RoleEntity roleEntity);

    RoleEntity mapDtoToEntity(RoleDto roleDto);
}
