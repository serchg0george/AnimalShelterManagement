package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.repositories.CageRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CageServiceTest {

    @Autowired
    CageService cageService;

    @Autowired
    CageRepository cageRepository;


    @Test
    void getAllCagesTest() {
        CageEntity cageEntity = new CageEntity("A2", "Available");
        CageEntity cageEntity1 = new CageEntity("A3", "Not Available");
        CageEntity cageEntity2 = new CageEntity("A4", "Available");

        cageRepository.save(cageEntity);
        cageRepository.save(cageEntity1);
        cageRepository.save(cageEntity2);
        List<CageDto> allCages = this.cageService.getAllCages();

        assertThat(allCages).hasSizeGreaterThan(1).hasSize(4);
    }

    @Test
    void createCageTest() {
        CageDto cageDto = new CageDto(null, "B1", "Available");

        CageDto savedCageDto = cageService.createCage(cageDto);

        assertThat(savedCageDto).isNotNull();
        assertThat(savedCageDto.cageNumber()).isEqualTo("B1");
        assertThat(savedCageDto.availability()).isEqualTo("Available");
    }

    @Test
    void getCageByIdTest() {
        CageEntity cageEntity = new CageEntity("C1", "Available");
        cageRepository.save(cageEntity);

        CageDto foundCageDto = cageService.getCageById(cageEntity.getId());

        assertThat(foundCageDto).isNotNull();
        assertThat(foundCageDto.cageNumber()).isEqualTo("C1");
        assertThat(foundCageDto.availability()).isEqualTo("Available");
    }

    @Test
    void updateCageTest() {
        CageEntity cageEntity = new CageEntity("D1", "Available");
        cageRepository.save(cageEntity);

        CageDto updateCageDto = new CageDto(cageEntity.getId(), "D2", "Not Available");

        CageDto updatedCageDto = cageService.updateCage(updateCageDto, cageEntity.getId());

        assertThat(updatedCageDto).isNotNull();
        assertThat(updatedCageDto.cageNumber()).isEqualTo("D2");
        assertThat(updatedCageDto.availability()).isEqualTo("Not Available");
    }

    @Test
    void deleteCageTest() {
        CageEntity cageEntity = new CageEntity("E1", "Available");
        cageRepository.save(cageEntity);

        cageService.deleteCage(cageEntity.getId());

        Optional<CageEntity> deletedCage = cageRepository.findById(cageEntity.getId());
        assertThat(deletedCage).isEmpty();
    }
}
