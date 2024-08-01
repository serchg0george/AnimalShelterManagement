package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;


public class RoleMapper {

    public RoleDto mapEntityToDto(RoleEntity entity) {
        if (entity == null) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
        roleDto.setDescription(entity.getDescription());

        return roleDto;
    }

    public RoleEntity mapDtoToEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId(dto.getId());
        roleEntity.setName(dto.getName());
        roleEntity.setDescription(dto.getDescription());

        return roleEntity;
    }
}
