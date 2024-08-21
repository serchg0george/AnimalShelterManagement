package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.UserDto;
import com.animalmanagementsystem.shelter.entities.UserEntity;
import com.animalmanagementsystem.shelter.exceptions.UserNotFoundException;
import com.animalmanagementsystem.shelter.mappers.impl.UserMapperImpl;
import com.animalmanagementsystem.shelter.repositories.UserRepository;
import com.animalmanagementsystem.shelter.searchs.UserSearchRequest;
import com.animalmanagementsystem.shelter.services.impl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapperImpl userMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setPhoneNumber("1234567890");

        userDto = new UserDto(1L, "john.doe@example.com", "password", "John", "Doe", "1234567890");
    }

    @Test
    void createUserTest() {
        when(userMapper.mapDtoToEntity(any(UserDto.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.mapEntityToDto(any(UserEntity.class))).thenReturn(userDto);

        UserDto savedUserDto = userService.createUser(userDto);

        assertThat(savedUserDto).isNotNull();
        assertThat(savedUserDto.firstName()).isEqualTo("John");
        assertThat(savedUserDto.lastName()).isEqualTo("Doe");
        assertThat(savedUserDto.email()).isEqualTo("john.doe@example.com");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void getUserByIdTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userMapper.mapEntityToDto(any(UserEntity.class))).thenReturn(userDto);

        UserDto foundUserDto = userService.getUserById(1L);

        assertThat(foundUserDto).isNotNull();
        assertThat(foundUserDto.firstName()).isEqualTo("John");
        assertThat(foundUserDto.lastName()).isEqualTo("Doe");
        assertThat(foundUserDto.email()).isEqualTo("john.doe@example.com");
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_NotFoundTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("User with id 1 not found.");
    }

    @Test
    void getAllUsersTest() {
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);
        userEntity2.setFirstName("Jane");
        userEntity2.setLastName("Doe");
        userEntity2.setEmail("jane.doe@example.com");
        userEntity2.setPassword("0987654321");
        userEntity2.setPhoneNumber("1234567890");

        when(userRepository.findAll()).thenReturn(List.of(userEntity, userEntity2));
        when(userMapper.mapEntityToDto(userEntity)).thenReturn(userDto);
        when(userMapper.mapEntityToDto(userEntity2)).thenReturn(new UserDto(2L, "jane.doe@example.com", "password", "Jane", "Doe", "1234567890"));

        List<UserDto> allUsers = userService.getAllUsers();

        assertThat(allUsers)
                .isNotNull()
                .hasSize(2)
                .extracting(UserDto::email)
                .containsExactlyInAnyOrder("john.doe@example.com", "jane.doe@example.com");

        verify(userRepository, times(1)).findAll();
    }


    @Test
    void updateUserTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userMapper.mapDtoToEntity(any(UserDto.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.mapEntityToDto(any(UserEntity.class))).thenReturn(userDto);

        UserDto updatedUserDto = userService.updateUser(userDto, 1L);

        assertThat(updatedUserDto).isNotNull();
        assertThat(updatedUserDto.firstName()).isEqualTo("John");
        assertThat(updatedUserDto.lastName()).isEqualTo("Doe");
        assertThat(updatedUserDto.email()).isEqualTo("john.doe@example.com");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void updateUser_NotFoundTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDto updateUserDto = new UserDto(1L, "John", "Doe", "john.doe@example.com", "password", "1234567890");

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(updateUserDto, 1L);
        });

        assertThat(exception.getMessage()).isEqualTo("User with id 1 not found.");
    }

    @Test
    void deleteUserTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_NotFoundTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("User with id 1 not found.");
    }

    @Test
    void findUserByCriteria_EmailTest() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<UserEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<UserEntity> root = mock(Root.class);
        TypedQuery<UserEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(UserEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(UserEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(userEntity));
        when(userMapper.mapEntityToDto(userEntity)).thenReturn(userDto);

        UserSearchRequest request = new UserSearchRequest("john.doe@example.com", null, null, null);

        List<UserDto> foundUsers = userService.findUserByCriteria(request);

        assertThat(foundUsers).isNotNull().hasSize(1).extracting(UserDto::email).contains("john.doe@example.com");
        verify(entityManager, times(1)).getCriteriaBuilder();
    }

    @Test
    void findUserByCriteria_FirstNameTest() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<UserEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<UserEntity> root = mock(Root.class);
        TypedQuery<UserEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(UserEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(UserEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(userEntity));
        when(userMapper.mapEntityToDto(userEntity)).thenReturn(userDto);

        UserSearchRequest request = new UserSearchRequest(null, "John", null, null);

        List<UserDto> foundUsers = userService.findUserByCriteria(request);

        assertThat(foundUsers).isNotNull().hasSize(1).extracting(UserDto::firstName).contains("John");
        verify(entityManager, times(1)).getCriteriaBuilder();
    }

    @Test
    void findUserByCriteria_LastNameTest() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<UserEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<UserEntity> root = mock(Root.class);
        TypedQuery<UserEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(UserEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(UserEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(userEntity));
        when(userMapper.mapEntityToDto(userEntity)).thenReturn(userDto);

        UserSearchRequest request = new UserSearchRequest(null, null, "Doe", null);

        List<UserDto> foundUsers = userService.findUserByCriteria(request);

        assertThat(foundUsers).isNotNull().hasSize(1).extracting(UserDto::lastName).contains("Doe");
        verify(entityManager, times(1)).getCriteriaBuilder();
    }

    @Test
    void findUserByCriteria_PhoneNumberTest() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<UserEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<UserEntity> root = mock(Root.class);
        TypedQuery<UserEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(UserEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(UserEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(userEntity));
        when(userMapper.mapEntityToDto(userEntity)).thenReturn(userDto);

        UserSearchRequest request = new UserSearchRequest(null, null, null, "1234567890");

        List<UserDto> foundUsers = userService.findUserByCriteria(request);

        assertThat(foundUsers).isNotNull().hasSize(1).extracting(UserDto::phoneNumber).contains("1234567890");
        verify(entityManager, times(1)).getCriteriaBuilder();
    }
}
