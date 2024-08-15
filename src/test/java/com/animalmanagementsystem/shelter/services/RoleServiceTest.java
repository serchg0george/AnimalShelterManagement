package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.repositories.RoleRepository;
import com.animalmanagementsystem.shelter.services.impl.RoleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class RoleServiceTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void createRoleTest() {
        RoleDto roleDto = new RoleDto(null, "Admin", "Administrator role");

        RoleDto savedRoleDto = roleService.createRole(roleDto);

        assertThat(savedRoleDto).isNotNull();
        assertThat(savedRoleDto.name()).isEqualTo("Admin");
        assertThat(savedRoleDto.description()).isEqualTo("Administrator role");
    }

    @Test
    void getRoleByIdTest() {
        RoleEntity roleEntity = new RoleEntity("User", "User role");
        roleRepository.save(roleEntity);
        RoleDto foundRoleDto = roleService.getRoleById(roleEntity.getId());

        assertThat(foundRoleDto).isNotNull();
        assertThat(foundRoleDto.name()).isEqualTo("User");
        assertThat(foundRoleDto.description()).isEqualTo("User role");
    }

    @Test
    void getAllRolesTest() {
        RoleEntity roleEntity1 = new RoleEntity("Admin", "Administrator role");
        RoleEntity roleEntity2 = new RoleEntity("Manager", "Manager role");

        roleRepository.save(roleEntity1);
        roleRepository.save(roleEntity2);

        List<RoleDto> allRoles = roleService.getAllRoles();

        assertThat(allRoles)
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo(2)
                .extracting("name").contains("Admin", "Manager");
    }

    @Test
    void updateRoleTest() {
        RoleEntity roleEntity = new RoleEntity("Guest", "Guest role");
        roleRepository.save(roleEntity);

        RoleDto updateRoleDto = new RoleDto(roleEntity.getId(), "Guest", "Updated Guest role");

        RoleDto updatedRoleDto = roleService.updateRole(updateRoleDto, roleEntity.getId());

        assertThat(updatedRoleDto).isNotNull();
        assertThat(updatedRoleDto.name()).isEqualTo("Guest");
        assertThat(updatedRoleDto.description()).isEqualTo("Updated Guest role");
    }

    @Test
    void deleteRoleTest() {
        RoleEntity roleEntity = new RoleEntity("Temporary", "Temporary role");
        roleRepository.save(roleEntity);

        roleService.deleteRole(roleEntity.getId());

        Optional<RoleEntity> deletedRole = roleRepository.findById(roleEntity.getId());
        assertThat(deletedRole).isEmpty();
    }

    @Test
    void getRoleById_NotFoundTest() {
        Long nonExistentId = 999L;
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.getRoleById(nonExistentId);
        });

        assertThat(exception.getMessage()).isEqualTo("Role not found");
    }
}

