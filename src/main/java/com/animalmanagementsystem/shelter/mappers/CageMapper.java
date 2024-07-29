package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CageMapper extends BaseMapper<CageEntity, CageDto> {
    @Override
    CageDto mapEntityToDto(CageEntity entity);

    @Override
    CageEntity mapDtoToEntity(CageDto dto);
}
