package com.animalmanagementsystem.shelter.mappers.impl;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.mappers.HealthMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HealthMapperImpl implements HealthMapper {

    @Override
    public HealthDto mapEntityToDto(HealthEntity entity) {
        if (entity == null) {
            return null;
        }

        Long id = null;
        String status = null;
        Date updateDate = null;

        id = entity.getId();
        status = entity.getStatus();
        updateDate = entity.getUpdateDate();

        return new HealthDto(id, status, updateDate);
    }

    @Override
    public HealthEntity mapDtoToEntity(HealthDto dto) {
        if (dto == null) {
            return null;
        }

        HealthEntity healthEntity = new HealthEntity();

        healthEntity.setId(dto.id());
        healthEntity.setStatus(dto.status());
        healthEntity.setUpdateDate(dto.updateDate());

        return healthEntity;
    }
}
