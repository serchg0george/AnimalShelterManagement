package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.RoleEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void findAllRolesTest() {
        RoleEntity roleEntity = new RoleEntity("admin", "admin role");
        roleRepository.save(roleEntity);

        List<RoleEntity> roles = roleRepository.findAll();

        assertThat(roles)
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .hasSize(2);
    }

    @Test
    void findRoleByIdTest() {
        RoleEntity roleEntity = new RoleEntity("admin", "admin role");
        roleRepository.save(roleEntity);
        assertThat(roleRepository.findById(roleEntity.getId())).isNotEmpty();
    }

    @Test
    void createRoleTest() {
        RoleEntity roleEntity = new RoleEntity("admin", "admin role");
        RoleEntity saved = roleRepository.save(roleEntity);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void deleteRoleTest() {
        RoleEntity roleEntity = new RoleEntity("admin", "admin role");
        roleRepository.save(roleEntity);
        roleRepository.delete(roleEntity);
        Optional<RoleEntity> entity = roleRepository.findById(roleEntity.getId());
        assertThat(entity).isEmpty();
    }
}
