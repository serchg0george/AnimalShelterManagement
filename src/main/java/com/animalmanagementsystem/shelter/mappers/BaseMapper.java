package com.animalmanagementsystem.shelter.mappers;

public interface BaseMapper <E, D>{
    D mapEntityToDto(E entity);
    E mapDtoToEntity(D dto);
}