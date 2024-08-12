package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        String email = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;

        id = entity.getId();
        email = entity.getEmail();
        password = entity.getPassword();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        phoneNumber = entity.getPhoneNumber();

        return new UserDto(id, email, password, firstName, lastName, phoneNumber);
    }

    public UserEntity mapDtoToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(dto.id());
        userEntity.setEmail(dto.email());
        userEntity.setPassword(dto.password());
        userEntity.setFirstName(dto.firstName());
        userEntity.setLastName(dto.lastName());
        userEntity.setPhoneNumber(dto.phoneNumber());

        return userEntity;
    }
}
