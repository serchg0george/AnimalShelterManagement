package com.animalmanagementsystem.shelter.mappers;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;

public interface CageMapper {
    CageDto mapEntityToDto(CageEntity cageEntity);

    CageEntity mapDtoToEntity(CageDto cageDto);
}
