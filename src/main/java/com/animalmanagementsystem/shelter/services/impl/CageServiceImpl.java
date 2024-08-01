package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.mappers.CageMapper;
import com.animalmanagementsystem.shelter.repository.CageRepository;
import com.animalmanagementsystem.shelter.services.CageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CageServiceImpl implements CageService {

    private final CageRepository cageRepository;
    private final CageMapper cageMapper;
    private static final String CAGE_NOT_FOUND_MESSAGE = "Cage not found";

    public CageServiceImpl(CageRepository cageRepository, CageMapper cageMapper) {
        this.cageRepository = cageRepository;
        this.cageMapper = cageMapper;
    }

    @Override
    public CageDto createCage(CageDto cageDto) {
        CageEntity cageEntity = cageMapper.mapDtoToEntity(cageDto);
        CageEntity savedCage = cageRepository.save(cageEntity);
        return cageMapper.mapEntityToDto(savedCage);
    }

    @Override
    public CageDto getCageById(Long id) {
        return cageRepository.findById(id)
                .map(cageMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(CAGE_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<CageDto> getAllCages() {
        List<CageEntity> cageEntities = cageRepository.findAll();
        return cageEntities.stream()
                .map(cageMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public CageDto updateCage(CageDto cageDto, Long id) {
        CageEntity cageEntity = cageMapper.mapDtoToEntity(cageDto);
        Optional<CageEntity> optionalCageEntity = cageRepository.findById(cageDto.getId());
        if (optionalCageEntity.isEmpty()) {
            throw new EntityNotFoundException(CAGE_NOT_FOUND_MESSAGE);
        }
        CageEntity updatedCageEntity = optionalCageEntity.get();
        updatedCageEntity.setCageNumber(cageEntity.getCageNumber());
        updatedCageEntity.setAvailability(cageEntity.getAvailability());
        updatedCageEntity.setId(id);
        return cageMapper.mapEntityToDto(cageRepository.save(updatedCageEntity));
    }

    @Override
    public void deleteCage(Long id) {
        Optional<CageEntity> optionalCageEntity = cageRepository.findById(id);
        if (optionalCageEntity.isEmpty()) {
            throw new EntityNotFoundException(CAGE_NOT_FOUND_MESSAGE);
        }
        cageRepository.deleteById(id);
    }
}
