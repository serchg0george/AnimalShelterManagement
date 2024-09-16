package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.exceptions.RoleNotFoundException;
import com.animalmanagementsystem.shelter.mappers.RoleMapper;
import com.animalmanagementsystem.shelter.repositories.RoleRepository;
import com.animalmanagementsystem.shelter.searchs.RoleSearchRequest;
import com.animalmanagementsystem.shelter.services.impl.RoleServiceImpl;
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
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private RoleServiceImpl roleService;

    private RoleEntity roleEntity;
    private RoleDto roleDto;

    @BeforeEach
    void setUp() {
        roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("Admin");
        roleEntity.setDescription("Administrator role");

        roleDto = new RoleDto(1L, "Admin", "Administrator role");
    }

    @Test
    void createRoleTest() {
        when(roleMapper.mapDtoToEntity(any(RoleDto.class))).thenReturn(roleEntity);
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);
        when(roleMapper.mapEntityToDto(any(RoleEntity.class))).thenReturn(roleDto);

        RoleDto savedRoleDto = roleService.createRole(roleDto);

        assertThat(savedRoleDto).isNotNull();
        assertThat(savedRoleDto.name()).isEqualTo("Admin");
        assertThat(savedRoleDto.description()).isEqualTo("Administrator role");
        verify(roleRepository, times(1)).save(any(RoleEntity.class));
    }

    @Test
    void getRoleByIdTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));
        when(roleMapper.mapEntityToDto(any(RoleEntity.class))).thenReturn(roleDto);

        RoleDto foundRoleDto = roleService.getRoleById(1L);

        assertThat(foundRoleDto).isNotNull();
        assertThat(foundRoleDto.name()).isEqualTo("Admin");
        assertThat(foundRoleDto.description()).isEqualTo("Administrator role");
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void getRoleById_NotFoundTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RoleNotFoundException.class, () -> {
            roleService.getRoleById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Role with id 1 not found.");
    }

    @Test
    void getAllRolesTest() {
        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setId(2L);
        roleEntity2.setName("Manager");
        roleEntity2.setDescription("Manager role");

        when(roleRepository.findAll()).thenReturn(List.of(roleEntity, roleEntity2));
        when(roleMapper.mapEntityToDto(any(RoleEntity.class))).thenReturn(roleDto, new RoleDto(2L, "Manager", "Manager role"));

        List<RoleDto> allRoles = roleService.getAllRoles();

        assertThat(allRoles)
                .isNotNull()
                .hasSize(2)
                .extracting(RoleDto::name)
                .contains("Admin", "Manager");
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void updateRoleTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));
        when(roleMapper.mapDtoToEntity(any(RoleDto.class))).thenReturn(roleEntity);
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);
        when(roleMapper.mapEntityToDto(any(RoleEntity.class))).thenReturn(roleDto);

        RoleDto updatedRoleDto = roleService.updateRole(roleDto, 1L);

        assertThat(updatedRoleDto).isNotNull();
        assertThat(updatedRoleDto.name()).isEqualTo("Admin");
        assertThat(updatedRoleDto.description()).isEqualTo("Administrator role");
        verify(roleRepository, times(1)).findById(1L);
        verify(roleRepository, times(1)).save(any(RoleEntity.class));
    }

    @Test
    void updateRole_NotFoundTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        RoleDto updateRoleDto = new RoleDto(1L, "Admin", "Administrator role");

        Exception exception = assertThrows(RoleNotFoundException.class, () -> {
            roleService.updateRole(updateRoleDto, 1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Role with id 1 not found.");
    }

    @Test
    void deleteRoleTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));

        roleService.deleteRole(1L);

        verify(roleRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteRole_NotFoundTest() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RoleNotFoundException.class, () -> {
            roleService.deleteRole(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Role with id 1 not found.");
    }

    @Test
    void findRoleByCriteria_NameTest() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<RoleEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<RoleEntity> root = mock(Root.class);
        TypedQuery<RoleEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(RoleEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(RoleEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(roleEntity));
        when(roleMapper.mapEntityToDto(roleEntity)).thenReturn(roleDto);

        RoleSearchRequest request = new RoleSearchRequest("Admin", null);

        List<RoleDto> foundRoles = roleService.findRoleByCriteria(request);

        assertThat(foundRoles).isNotNull().hasSize(1).extracting(RoleDto::name).contains("Admin");
        verify(entityManager, times(1)).getCriteriaBuilder();
    }

    @Test
    void findRoleByCriteria_DescriptionTest() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<RoleEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<RoleEntity> root = mock(Root.class);
        TypedQuery<RoleEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(RoleEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(RoleEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(roleEntity));
        when(roleMapper.mapEntityToDto(roleEntity)).thenReturn(roleDto);

        RoleSearchRequest request = new RoleSearchRequest(null, "Administrator role");

        List<RoleDto> foundRoles = roleService.findRoleByCriteria(request);

        assertThat(foundRoles).isNotNull().hasSize(1).extracting(RoleDto::description).contains("Administrator role");
        verify(entityManager, times(1)).getCriteriaBuilder();
    }
}
