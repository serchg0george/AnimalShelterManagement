package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import org.springframework.stereotype.Component;

@Component
public class HealthMapper {

    public HealthDto mapEntityToDto(HealthEntity entity) {
        if (entity == null) {
            return null;
        }

        HealthDto healthDto = new HealthDto();

        healthDto.setId(entity.getId());
        healthDto.setStatus(entity.getStatus());
        healthDto.setUpdateDate(entity.getUpdateDate());

        return healthDto;
    }

    public HealthEntity mapDtoToEntity(HealthDto dto) {
        if (dto == null) {
            return null;
        }

        HealthEntity healthEntity = new HealthEntity();

        healthEntity.setId(dto.getId());
        healthEntity.setStatus(dto.getStatus());
        healthEntity.setUpdateDate(dto.getUpdateDate());

        return healthEntity;
    }
}
