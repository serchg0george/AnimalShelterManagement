package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.mappers.impl.HealthMapperImpl;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HealthMapperTest {
    private final HealthMapper healthMapper = new HealthMapperImpl();

    @Test
    void checkNullDto() {
        HealthDto healthDto = healthMapper.mapEntityToDto(null);

        assertNull(healthDto);
    }

    @Test
    void checkNullEntity() {
        HealthEntity healthEntity = healthMapper.mapDtoToEntity(null);

        assertNull(healthEntity);
    }

    @Test
    void checkEntityToDto() {
        HealthEntity healthEntity = new HealthEntity("Checked", Date.valueOf(LocalDate.now()));
        HealthDto healthDto = new HealthDto(null, "Checked", Date.valueOf(LocalDate.now()));

        HealthDto expectedDto = healthMapper.mapEntityToDto(healthEntity);

        assertEquals(healthDto, expectedDto);
    }

    @Test
    void checkDtoToEntity() {
        HealthEntity healthEntity = new HealthEntity("Checked", Date.valueOf(LocalDate.now()));
        HealthDto healthDto = new HealthDto(null, "Checked", Date.valueOf(LocalDate.now()));

        HealthEntity expectedEntity = healthMapper.mapDtoToEntity(healthDto);

        assertEquals(healthEntity, expectedEntity);
    }
}
