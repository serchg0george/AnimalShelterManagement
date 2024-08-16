package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;

public interface UserMapper {
    UserDto mapEntityToDto(UserEntity userEntity);

    UserEntity mapDtoToEntity(UserDto userDto);
}
