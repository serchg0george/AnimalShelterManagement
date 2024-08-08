package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;

public class RoleMapper {

    public RoleDto mapEntityToDto(RoleEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();

        return new RoleDto(id, name, description);
    }

    public RoleEntity mapDtoToEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId(dto.id());
        roleEntity.setName(dto.name());
        roleEntity.setDescription(dto.description());

        return roleEntity;
    }
}
