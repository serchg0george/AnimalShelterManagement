package com.animalmanagementsystem.shelter.mappers.impl;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.mappers.CageMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CageMapperImpl implements CageMapper {

    @Override
    public CageDto mapEntityToDto(@NotNull CageEntity entity) {

        Objects.requireNonNull(entity);

        Long id = null;
        String cageNumber = null;
        String availability = null;

        id = entity.getId();
        cageNumber = entity.getCageNumber();
        availability = entity.getAvailability();

        return new CageDto(id, cageNumber, availability);
    }

    @Override
    public CageEntity mapDtoToEntity(@NotNull CageDto dto) {

        Objects.requireNonNull(dto);

        CageEntity cageEntity = new CageEntity();

        cageEntity.setId(dto.id());
        cageEntity.setCageNumber(dto.cageNumber());
        cageEntity.setAvailability(dto.availability());

        return cageEntity;
    }
}
