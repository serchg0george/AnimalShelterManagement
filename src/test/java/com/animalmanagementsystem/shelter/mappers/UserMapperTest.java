package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    void checkNullDto() {
        UserDto userDto = userMapper.mapEntityToDto(null);

        assertNull(userDto);
    }

    @Test
    void checkNullEntity() {
        UserEntity userEntity = userMapper.mapDtoToEntity(null);

        assertNull(userEntity);
    }

    @Test
    void checkEntityToDto() {
        UserEntity userEntity = new UserEntity("email", "password", "firstname",
                "lastname", "123456789");
        UserDto userDto = new UserDto(null, "email", "password", "firstname",
                "lastname", "123456789");

        UserDto expectedDto = userMapper.mapEntityToDto(userEntity);

        assertEquals(userDto, expectedDto);
    }

    @Test
    void checkDtoToEntity() {
        UserEntity userEntity = new UserEntity("email", "password", "firstname",
                "lastname", "123456789");
        UserDto userDto = new UserDto(null, "email", "password", "firstname",
                "lastname", "123456789");

        UserEntity expectedEntity = userMapper.mapDtoToEntity(userDto);

        assertEquals(userEntity, expectedEntity);
    }
}
