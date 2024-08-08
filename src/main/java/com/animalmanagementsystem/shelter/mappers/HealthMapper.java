package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HealthMapper {

    public HealthDto mapEntityToDto(HealthEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        String status = null;

        id = entity.getId();
        status = entity.getStatus();

        Date updateDate = null;

        return new HealthDto(id, status, updateDate);
    }

    public HealthEntity mapDtoToEntity(HealthDto dto) {
        if (dto == null) {
            return null;
        }

        HealthEntity healthEntity = new HealthEntity();

        healthEntity.setId(dto.id());
        healthEntity.setStatus(dto.status());

        return healthEntity;
    }
}
