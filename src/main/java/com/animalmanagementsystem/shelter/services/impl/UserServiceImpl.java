package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.mappers.UserMapper;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import com.animalmanagementsystem.shelter.searchs.UserSearchRequest;
import com.animalmanagementsystem.shelter.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserDto> findUserByCriteria(UserSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        if (request.email() != null && !request.email().isBlank()) {
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + request.email() + "%");
            predicates.add(emailPredicate);
        } else if (request.firstName() != null && !request.firstName().isBlank()) {
            Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%"
                    + request.firstName() + "%");
            predicates.add(firstNamePredicate);
        } else if (request.lastName() != null && !request.lastName().isBlank()) {
            Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%"
                    + request.lastName() + "%");
            predicates.add(lastNamePredicate);
        } else if (request.phoneNumber() != null && !request.phoneNumber().isBlank()) {
            Predicate phoneNumberPredicate = criteriaBuilder.like(root.get("phoneNumber"), "%"
                    + request.phoneNumber() + "%");
            predicates.add(phoneNumberPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<UserEntity> query = entityManager.createQuery(criteriaQuery);


        return query.getResultList().stream()
                .map(userMapper::mapEntityToDto)
                .toList();
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
