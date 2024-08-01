package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.HealthDto;

import java.util.List;

public interface HealthService {
    HealthDto createHealth(HealthDto healthDto);

    HealthDto getHealthById(Long id);

    List<HealthDto> getAllHealths();

    HealthDto updateHealth(HealthDto healthDto, Long id);

    void deleteHealth(Long id);
}
