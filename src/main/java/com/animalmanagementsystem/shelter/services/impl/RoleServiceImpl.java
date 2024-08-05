package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.mappers.RoleMapper;
import com.animalmanagementsystem.shelter.repository.RoleRepository;
import com.animalmanagementsystem.shelter.services.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_NOT_FOUND_MESSAGE = "Role not found";

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        RoleEntity roleEntity = roleMapper.mapDtoToEntity(roleDto);
        RoleEntity savedRole = roleRepository.save(roleEntity);
        return roleMapper.mapEntityToDto(savedRole);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream()
                .map(roleMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long id) {
        RoleEntity roleEntity = roleMapper.mapDtoToEntity(roleDto);
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(roleDto.getId());
        if (optionalRoleEntity.isEmpty()) {
            throw new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE);
        }

        RoleEntity updatedRoleEntity = optionalRoleEntity.get();

        updatedRoleEntity.setId(id);
        updatedRoleEntity.setName(roleEntity.getName());
        updatedRoleEntity.setDescription(roleEntity.getDescription());
        return roleMapper.mapEntityToDto(roleRepository.save(updatedRoleEntity));
    }

    @Override
    public void deleteRole(Long id) {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(id);
        if (optionalRoleEntity.isEmpty()) {
            throw new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE);
        }
        roleRepository.deleteById(id);
    }
}
