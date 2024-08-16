package com.animalmanagementsystem.shelter.mappers.impl;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.mappers.CageMapper;
import org.springframework.stereotype.Component;

@Component
public class CageMapperImpl implements CageMapper {

    @Override
    public CageDto mapEntityToDto(CageEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        String cageNumber = null;
        String availability = null;

        id = entity.getId();
        cageNumber = entity.getCageNumber();
        availability = entity.getAvailability();

        return new CageDto(id, cageNumber, availability);
    }

    @Override
    public CageEntity mapDtoToEntity(CageDto dto) {
        if (dto == null) {
            return null;
        }

        CageEntity cageEntity = new CageEntity();

        cageEntity.setId(dto.id());
        cageEntity.setCageNumber(dto.cageNumber());
        cageEntity.setAvailability(dto.availability());

        return cageEntity;
    }
}
