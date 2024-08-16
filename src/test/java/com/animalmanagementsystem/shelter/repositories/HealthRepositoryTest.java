package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.HealthEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class HealthRepositoryTest {

    @Autowired
    HealthRepository healthRepository;

    @Test
    void findAllHealthTest() {

        HealthEntity healthEntity = new HealthEntity("healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        List<HealthEntity> healthList = healthRepository.findAll();

        assertThat(healthList)
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .hasSize(3);

    }

    @Test
    void findHealthById() {
        HealthEntity healthEntity = new HealthEntity("healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);
        assertThat(healthRepository.findById(healthEntity.getId())).isNotEmpty();
    }

    @Test
    void createHealthTest() {
        HealthEntity healthEntity = new HealthEntity("healthy", Date.valueOf(LocalDate.now()));
        HealthEntity saved = healthRepository.save(healthEntity);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void deleteHealthTest() {
        HealthEntity healthEntity = new HealthEntity("healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);
        healthRepository.delete(healthEntity);
        Optional<HealthEntity> entity = healthRepository.findById(healthEntity.getId());
        assertThat(entity).isEmpty();
    }
}
