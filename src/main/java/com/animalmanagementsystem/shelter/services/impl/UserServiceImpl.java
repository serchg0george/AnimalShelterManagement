package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.mappers.UserMapper;
import com.animalmanagementsystem.shelter.repository.UserRepository;
import com.animalmanagementsystem.shelter.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.mapEntityToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userDto.id());
        if (optionalUserEntity.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
        }

        UserEntity updatedUserEntity = optionalUserEntity.get();
        updatedUserEntity.setFirstName(userEntity.getFirstName());
        updatedUserEntity.setLastName(userEntity.getLastName());
        updatedUserEntity.setEmail(userEntity.getEmail());
        updatedUserEntity.setPassword(userEntity.getPassword());
        updatedUserEntity.setPhoneNumber(userEntity.getPhoneNumber());
        updatedUserEntity.setId(id);
        return userMapper.mapEntityToDto(userRepository.save(updatedUserEntity));
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        userRepository.deleteById(id);
    }
}
