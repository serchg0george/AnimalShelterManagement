package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;

public class UserMapper {

    public UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setPhoneNumber(entity.getPhoneNumber());

        return userDto;
    }

    public UserEntity mapDtoToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(dto.getId());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());

        return userEntity;
    }
}
