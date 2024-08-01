package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import org.springframework.stereotype.Component;

@Component
public class CageMapper {

    public CageDto mapEntityToDto(CageEntity entity) {
        if (entity == null) {
            return null;
        }

        CageDto cageDto = new CageDto();

        cageDto.setId(entity.getId());
        cageDto.setCageNumber(entity.getCageNumber());
        cageDto.setAvailability(entity.getAvailability());

        return cageDto;
    }

    public CageEntity mapDtoToEntity(CageDto dto) {
        if (dto == null) {
            return null;
        }

        CageEntity cageEntity = new CageEntity();

        cageEntity.setId(dto.getId());
        cageEntity.setCageNumber(dto.getCageNumber());
        cageEntity.setAvailability(dto.getAvailability());

        return cageEntity;
    }
}
