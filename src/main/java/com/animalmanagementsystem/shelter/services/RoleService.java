package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();

    RoleDto updateRole(RoleDto roleDto, Long id);

    void deleteRole(Long id);
}
