package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.searchs.RoleSearchRequest;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles(int pageNo, int pageSize);

    RoleDto updateRole(RoleDto roleDto, Long id);

    void deleteRole(Long id);

    List<RoleDto> findRoleByCriteria(RoleSearchRequest request);
}
