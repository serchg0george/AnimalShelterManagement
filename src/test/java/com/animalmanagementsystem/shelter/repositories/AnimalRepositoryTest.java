package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AnimalRepositoryTest {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    CageRepository cageRepository;

    @Autowired
    HealthRepository healthRepository;

    @Test
    void findAllAnimalsTest() {
        CageEntity cageEntity = new CageEntity("B1", "Available");
        cageRepository.save(cageEntity);

        HealthEntity healthEntity = new HealthEntity("Healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        AnimalEntity animal1 = new AnimalEntity("Max", "Dog", 5, cageEntity, Collections.emptyList(), healthEntity);
        animalRepository.save(animal1);

        List<AnimalEntity> animalList = animalRepository.findAll();

        assertThat(animalList)
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .hasSize(2);
    }

    @Test
    void findAnimalByIdTest() {
        CageEntity cageEntity = new CageEntity("B1", "Available");
        cageRepository.save(cageEntity);

        HealthEntity healthEntity = new HealthEntity("Healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        AnimalEntity animal = new AnimalEntity("Max", "Dog", 5, cageEntity, Collections.emptyList(), healthEntity);
        animalRepository.save(animal);

        assertThat(animalRepository.findById(animal.getId())).isNotEmpty();
    }

    @Test
    void createAnimalTest() {
        CageEntity cageEntity = new CageEntity("B1", "Available");
        cageRepository.save(cageEntity);

        HealthEntity healthEntity = new HealthEntity("Healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        AnimalEntity animal = new AnimalEntity("Max", "Dog", 5, cageEntity, Collections.emptyList(), healthEntity);

        AnimalEntity saved = animalRepository.save(animal);

        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void deleteAnimalTest() {
        CageEntity cageEntity = new CageEntity("B1", "Available");
        cageRepository.save(cageEntity);

        HealthEntity healthEntity = new HealthEntity("Healthy", Date.valueOf(LocalDate.now()));
        healthRepository.save(healthEntity);

        AnimalEntity animal = new AnimalEntity("Max", "Dog", 5, cageEntity, Collections.emptyList(), healthEntity);
        animalRepository.save(animal);

        animalRepository.delete(animal);

        Optional<AnimalEntity> entity = animalRepository.findById(animal.getId());
        assertThat(entity).isEmpty();
    }
}

