package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.mappers.impl.CageMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CageMapperTest {
    private final CageMapper cageMapper = new CageMapperImpl();

    @Test
    void checkNullDto() {
        assertThrows(NullPointerException.class, () -> cageMapper.mapDtoToEntity(null));
    }

    @Test
    void checkNullEntity() {
        assertThrows(NullPointerException.class, () -> cageMapper.mapEntityToDto(null));
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
