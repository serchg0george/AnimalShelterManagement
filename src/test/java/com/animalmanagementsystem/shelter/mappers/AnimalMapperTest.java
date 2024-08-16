package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.mappers.impl.AnimalMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnimalMapperTest {
    private final AnimalMapper animalMapper = new AnimalMapperImpl();

    @Test
    void checkNullDto() {
        AnimalDto animalDto = animalMapper.mapEntityToDto(null);

        assertNull(animalDto);
    }

    @Test
    void checkNullEntity() {
        AnimalEntity animalEntity = animalMapper.mapDtoToEntity(null);

        assertNull(animalEntity);
    }

    @Test
    void checkEntityToDto() {
        AnimalEntity animalEntity = new AnimalEntity("Name", "Species", 1, null, null, null);
        AnimalDto animalDto = new AnimalDto(null, "Name", "Species", 1, null, null, null);

        AnimalDto expectedDto = animalMapper.mapEntityToDto(animalEntity);

        assertEquals(animalDto, expectedDto);
    }

    @Test
    void checkDtoToEntity() {
        AnimalEntity animalEntity = new AnimalEntity("Name", "Species", 1, null, null, null);
        AnimalDto animalDto = new AnimalDto(null, "Name", "Species", 1, null, null, null);

        AnimalEntity expectedEntity = animalMapper.mapDtoToEntity(animalDto);

        assertEquals(animalEntity, expectedEntity);
    }
}
