package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.searchs.CageSearchRequest;

import java.util.List;

public interface CageService {
    CageDto createCage(CageDto cageDto);

    CageDto getCageById(Long id);

    List<CageDto> getAllCages();

    CageDto updateCage(CageDto cageDto, Long id);

    void deleteCage(Long id);

    List<CageDto> findCageByCriteria(CageSearchRequest request);
}
