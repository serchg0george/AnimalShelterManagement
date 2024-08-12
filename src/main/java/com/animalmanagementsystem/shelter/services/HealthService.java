package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.searchs.HealthSearchRequest;

import java.util.List;

public interface HealthService {
    HealthDto createHealth(HealthDto healthDto);

    HealthDto getHealthById(Long id);

    List<HealthDto> getAllHealths();

    HealthDto updateHealth(HealthDto healthDto, Long id);

    void deleteHealth(Long id);

    List<HealthDto> findHealthByCriteria(HealthSearchRequest request);
}
