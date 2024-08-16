package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserRoleDto;
import com.animalmanagementsystem.shelter.entities.UserRoleEntity;

public interface UserRoleMapper {
    UserRoleDto mapEntityToDto(UserRoleEntity userRoleEntity);

    UserRoleEntity mapDtoToEntity(UserRoleDto dto);
}
