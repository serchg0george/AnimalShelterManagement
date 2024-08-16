package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.CageEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CageRepositoryTest {

    @Autowired
    CageRepository cageRepository;

    @Test
    void findAllCagesTest() {

        CageEntity cageEntity = new CageEntity("A2", "Available");
        cageRepository.save(cageEntity);

        List<CageEntity> cageList = cageRepository.findAll();

        assertThat(cageList)
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .hasSize(2);

    }

    @Test
    void findCageByIdTest() {
        CageEntity cageEntity = new CageEntity("A2", "Available");
        cageRepository.save(cageEntity);
        assertThat(cageRepository.findById(cageEntity.getId())).isNotEmpty();
    }

    @Test
    void createCageTest() {
        CageEntity cageEntity = new CageEntity("A2", "Available");
        CageEntity saved = cageRepository.save(cageEntity);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void deleteCage() {
        CageEntity cageEntity = new CageEntity("A2", "Available");
        cageRepository.save(cageEntity);
        cageRepository.delete(cageEntity);
        Optional<CageEntity> entity = cageRepository.findById(cageEntity.getId());
        assertThat(entity).isEmpty();
    }

}
