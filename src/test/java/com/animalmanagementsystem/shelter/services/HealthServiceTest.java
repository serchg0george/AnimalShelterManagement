package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.repositories.HealthRepository;
import com.animalmanagementsystem.shelter.services.impl.HealthServiceImpl;
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
class HealthServiceTest {

    @Autowired
    HealthServiceImpl healthService;

    @Autowired
    HealthRepository healthRepository;

    @Test
    void createHealthTest() {
        HealthDto healthDto = new HealthDto(null, "Healthy", Date.valueOf(LocalDate.now()));

        HealthDto savedHealthDto = healthService.createHealth(healthDto);

        assertThat(savedHealthDto).isNotNull();
        assertThat(savedHealthDto.status()).isEqualTo("Healthy");
        assertThat(savedHealthDto.updateDate()).isEqualTo(Date.valueOf(LocalDate.now()));
    }

    @Test
    void getHealthByIdTest() {
        HealthEntity healthEntity = new HealthEntity("Sick", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        HealthDto foundHealthDto = healthService.getHealthById(healthEntity.getId());

        assertThat(foundHealthDto).isNotNull();
        assertThat(foundHealthDto.status()).isEqualTo("Sick");
        assertThat(foundHealthDto.updateDate()).isEqualTo(Date.valueOf(LocalDate.now()));
    }

    @Test
    void getAllHealthsTest() {
        HealthEntity healthEntity1 = new HealthEntity("Healthy", Date.valueOf(LocalDate.now()));
        HealthEntity healthEntity2 = new HealthEntity("Injured", Date.valueOf(LocalDate.now()));

        healthRepository.save(healthEntity1);
        healthRepository.save(healthEntity2);

        List<HealthDto> allHealths = healthService.getAllHealths();

        assertThat(allHealths).isNotNull();
        assertThat(allHealths)
                .hasSizeGreaterThanOrEqualTo(2)
                .extracting("status").contains("Healthy", "Injured");
    }

    @Test
    void updateHealthTest() {
        HealthEntity healthEntity = new HealthEntity("Injured", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        HealthDto updateHealthDto = new HealthDto(healthEntity.getId(), "Recovered", Date.valueOf(LocalDate.now()));

        HealthDto updatedHealthDto = healthService.updateHealth(updateHealthDto, healthEntity.getId());

        assertThat(updatedHealthDto).isNotNull();
        assertThat(updatedHealthDto.status()).isEqualTo("Recovered");
        assertThat(updatedHealthDto.updateDate()).isEqualTo(Date.valueOf(LocalDate.now()));
    }

    @Test
    void deleteHealthTest() {
        HealthEntity healthEntity = new HealthEntity("Critical", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        healthService.deleteHealth(healthEntity.getId());

        Optional<HealthEntity> deletedHealth = healthRepository.findById(healthEntity.getId());
        assertThat(deletedHealth).isEmpty();
    }
}
