package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.CageDto;

import java.util.List;

public interface CageService {
    CageDto createCage(CageDto cageDto);

    CageDto getCageById(Long id);

    List<CageDto> getAllCages();

    CageDto updateCage(CageDto cageDto);

    void deleteCage(Long id);
}
