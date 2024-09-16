package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.UserRoleDto;
import com.animalmanagementsystem.shelter.entities.UserRoleEntity;
import com.animalmanagementsystem.shelter.exceptions.UserRoleNotFoundException;
import com.animalmanagementsystem.shelter.mappers.UserRoleMapper;
import com.animalmanagementsystem.shelter.repositories.UserRoleRepository;
import com.animalmanagementsystem.shelter.services.UserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    @Transactional
    public UserRoleDto createUserRole(UserRoleDto userRoleDto) {
        UserRoleEntity userRoleEntity = userRoleMapper.mapDtoToEntity(userRoleDto);
        UserRoleEntity savedUserRole = userRoleRepository.save(userRoleEntity);
        return userRoleMapper.mapEntityToDto(savedUserRole);
    }

    @Override
    public UserRoleDto getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .map(userRoleMapper::mapEntityToDto)
                .orElseThrow(() -> new UserRoleNotFoundException(id));
    }

    @Override
    public List<UserRoleDto> getAllUserRoles() {
        List<UserRoleEntity> userRoleEntities = userRoleRepository.findAll();
        return userRoleEntities.stream()
                .map(userRoleMapper::mapEntityToDto)
                .toList();
    }

    @Override
    @Transactional
    public UserRoleDto updateUserRole(UserRoleDto userRoleDto, Long id) {
        UserRoleEntity userRoleEntity = userRoleMapper.mapDtoToEntity(userRoleDto);
        Optional<UserRoleEntity> optionalUserRoleEntity = userRoleRepository.findById(userRoleDto.id());
        if (optionalUserRoleEntity.isEmpty()) {
            throw new UserRoleNotFoundException(id);
        }

        UserRoleEntity updatedUserRoleEntity = optionalUserRoleEntity.get();
        updatedUserRoleEntity.setId(id);
        updatedUserRoleEntity.setRoles(userRoleEntity.getRoles());
        updatedUserRoleEntity.setUsers(userRoleEntity.getUsers());
        return userRoleMapper.mapEntityToDto(userRoleRepository.save(updatedUserRoleEntity));
    }

    @Override
    @Transactional
    public void deleteUserRole(Long id) {
        Optional<UserRoleEntity> optionalUserRoleEntity = userRoleRepository.findById(id);
        if (optionalUserRoleEntity.isEmpty()) {
            throw new UserRoleNotFoundException(id);
        }
        userRoleRepository.deleteById(id);
    }
}
