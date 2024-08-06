package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.mappers.HealthMapper;
import com.animalmanagementsystem.shelter.repository.HealthRepository;
import com.animalmanagementsystem.shelter.services.HealthService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthServiceImpl implements HealthService {

    private final HealthRepository healthRepository;
    private final HealthMapper healthMapper;
    private static final String HEALTH_NOT_FOUND_MESSAGE = "Health Not Found";

    public HealthServiceImpl(HealthRepository healthRepository, HealthMapper healthMapper) {
        this.healthRepository = healthRepository;
        this.healthMapper = healthMapper;
    }

    @Override
    public HealthDto createHealth(HealthDto healthDto) {
        HealthEntity healthEntity = healthMapper.mapDtoToEntity(healthDto);
        HealthEntity savedHealth = healthRepository.save(healthEntity);
        return healthMapper.mapEntityToDto(savedHealth);
    }

    @Override
    public HealthDto getHealthById(Long id) {
        return healthRepository.findById(id)
                .map(healthMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(HEALTH_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<HealthDto> getAllHealths() {
        List<HealthEntity> healthEntities = healthRepository.findAll();
        return healthEntities.stream()
                .map(healthMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public HealthDto updateHealth(HealthDto healthDto, Long id) {
        HealthEntity healthEntity = healthMapper.mapDtoToEntity(healthDto);
        Optional<HealthEntity> optionalHealthEntity = healthRepository.findById(healthDto.id());
        if (optionalHealthEntity.isEmpty()) {
            throw new EntityNotFoundException(HEALTH_NOT_FOUND_MESSAGE);
        }
        HealthEntity updatedHealthEntity = optionalHealthEntity.get();
        updatedHealthEntity.setStatus(healthEntity.getStatus());
        updatedHealthEntity.setUpdateDate(healthEntity.getUpdateDate());
        updatedHealthEntity.setId(id);
        return healthMapper.mapEntityToDto(healthRepository.save(updatedHealthEntity));
    }

    @Override
    public void deleteHealth(Long id) {
        Optional<HealthEntity> optionalHealthEntity = healthRepository.findById(id);
        if (optionalHealthEntity.isEmpty()) {
            throw new EntityNotFoundException(HEALTH_NOT_FOUND_MESSAGE);
        }
        healthRepository.deleteById(id);
    }
}
