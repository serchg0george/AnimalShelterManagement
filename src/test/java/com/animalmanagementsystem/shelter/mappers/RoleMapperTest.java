package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.mappers.impl.RoleMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RoleMapperTest {
    private final RoleMapper roleMapper = new RoleMapperImpl();

    @Test
    void checkNullDto() {
        RoleDto roleDto = roleMapper.mapEntityToDto(null);

        assertNull(roleDto);
    }

    @Test
    void checkNullEntity() {
        RoleEntity roleEntity = roleMapper.mapDtoToEntity(null);

        assertNull(roleEntity);
    }

    @Test
    void checkEntityToDto() {
        RoleEntity roleEntity = new RoleEntity("Name", "Description");
        RoleDto roleDto = new RoleDto(null, "Name", "Description");

        RoleDto expectedDto = roleMapper.mapEntityToDto(roleEntity);

        assertEquals(roleDto, expectedDto);
    }

    @Test
    void checkDtoToEntity() {
        RoleEntity roleEntity = new RoleEntity("Name", "Description");
        RoleDto roleDto = new RoleDto(null, "Name", "Description");

        RoleEntity expectedEntity = roleMapper.mapDtoToEntity(roleDto);
        assertEquals(roleEntity, expectedEntity);
    }
}
