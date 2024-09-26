package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.exceptions.UserNotFoundException;
import com.animalmanagementsystem.shelter.mappers.UserMapper;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import com.animalmanagementsystem.shelter.searchs.UserSearchRequest;
import com.animalmanagementsystem.shelter.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserDto> findUserByCriteria(final UserSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"), query);
            Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), query);
            Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), query);
            Predicate phoneNumberPredicate = criteriaBuilder.like(root.get("phoneNumber"), query);

            predicates.add(criteriaBuilder.or(emailPredicate, firstNamePredicate, lastNamePredicate, phoneNumberPredicate));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        TypedQuery<UserEntity> query = entityManager.createQuery(criteriaQuery);


        return query.getResultList().stream().map(userMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.mapEntityToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::mapEntityToDto).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, Long id) {
        UserEntity userEntity = userMapper.mapDtoToEntity(userDto);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userDto.id());

        UserEntity updatedUserEntity = optionalUserEntity.orElseThrow(() -> new UserNotFoundException(id));

        updatedUserEntity.setFirstName(userEntity.getFirstName());
        updatedUserEntity.setLastName(userEntity.getLastName());
        updatedUserEntity.setEmail(userEntity.getEmail());
        updatedUserEntity.setPassword(userEntity.getPassword());
        updatedUserEntity.setPhoneNumber(userEntity.getPhoneNumber());
        updatedUserEntity.setId(id);
        return userMapper.mapEntityToDto(userRepository.save(updatedUserEntity));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

        optionalUserEntity.orElseThrow(() -> new UserNotFoundException(id));

        userRepository.deleteById(id);
    }
}
