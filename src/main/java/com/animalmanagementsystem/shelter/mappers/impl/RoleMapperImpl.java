package com.animalmanagementsystem.shelter.mappers.impl;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.mappers.RoleMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
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

    @Override
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
