package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CageMapperTest {
    private final CageMapper cageMapper = new CageMapper();

    @Test
    void checkNullDto() {
        CageDto cageDto = cageMapper.mapEntityToDto(null);

        assertNull(cageDto);
    }

    @Test
    void checkNullEntity() {
        CageEntity cageEntity = cageMapper.mapDtoToEntity(null);

        assertNull(cageEntity);
    }

    @Test
    void checkEntityToDtoMapping() {
        CageEntity cageEntity = new CageEntity("A1", "Available");
        CageDto cageDto = new CageDto(null, "A1", "Available");

        CageDto expectedDto = cageMapper.mapEntityToDto(cageEntity);

        assertEquals(cageDto, expectedDto);
    }

    @Test
    void checkDtoToEntityMapping() {
        CageEntity cageEntity = new CageEntity("A1", "Available");
        CageDto cageDto = new CageDto(null, "A1", "Available");

        CageEntity expectedEntity = cageMapper.mapDtoToEntity(cageDto);

        assertEquals(cageEntity, expectedEntity);
    }
}
