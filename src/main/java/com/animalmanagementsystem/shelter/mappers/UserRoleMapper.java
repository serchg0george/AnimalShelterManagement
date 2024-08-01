package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.dtos.UserRoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.entities.UserRoleEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleDto mapEntityToDto(UserRoleEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDto user = null;
        RoleDto role = null;

        UserRoleDto userRoleDto = new UserRoleDto(user, role);

        userRoleDto.setId(entity.getId());

        return userRoleDto;
    }

    public UserRoleEntity mapDtoToEntity(UserRoleDto dto) {
        if (dto == null) {
            return null;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setId(dto.getId());
        userRoleEntity.setUsers(userDtoToUserEntity(dto.getUsers()));
        userRoleEntity.setRoles(roleDtoToRoleEntity(dto.getRoles()));

        return userRoleEntity;
    }

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());

        return userEntity;
    }

    protected RoleEntity roleDtoToRoleEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId(roleDto.getId());
        roleEntity.setName(roleDto.getName());
        roleEntity.setDescription(roleDto.getDescription());

        return roleEntity;
    }
}
