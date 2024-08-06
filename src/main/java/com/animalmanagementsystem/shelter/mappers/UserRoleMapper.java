package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.dtos.UserRoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.entities.UserRoleEntity;

public class UserRoleMapper {

    public UserRoleDto mapEntityToDto(UserRoleEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        UserDto users = null;
        RoleDto roles = null;

        id = entity.getId();
        users = userEntityToUserDto(entity.getUsers());
        roles = roleEntityToRoleDto(entity.getRoles());

        return new UserRoleDto(id, users, roles);
    }

    public UserRoleEntity mapDtoToEntity(UserRoleDto dto) {
        if (dto == null) {
            return null;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setId(dto.id());
        userRoleEntity.setUsers(userDtoToUserEntity(dto.users()));
        userRoleEntity.setRoles(roleDtoToRoleEntity(dto.roles()));

        return userRoleEntity;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        Long id = null;
        String email = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;

        id = userEntity.getId();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        firstName = userEntity.getFirstName();
        lastName = userEntity.getLastName();
        phoneNumber = userEntity.getPhoneNumber();

        return new UserDto(id, email, password, firstName, lastName, phoneNumber);
    }

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = roleEntity.getId();
        name = roleEntity.getName();
        description = roleEntity.getDescription();

        return new RoleDto(id, name, description);
    }

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.id());
        userEntity.setEmail(userDto.email());
        userEntity.setPassword(userDto.password());
        userEntity.setFirstName(userDto.firstName());
        userEntity.setLastName(userDto.lastName());
        userEntity.setPhoneNumber(userDto.phoneNumber());

        return userEntity;
    }

    protected RoleEntity roleDtoToRoleEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId(roleDto.id());
        roleEntity.setName(roleDto.name());
        roleEntity.setDescription(roleDto.description());

        return roleEntity;
    }
}
