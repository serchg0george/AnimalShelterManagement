package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.mappers.CageMapper;
import com.animalmanagementsystem.shelter.repositories.CageRepository;
import com.animalmanagementsystem.shelter.searchs.CageSearchRequest;
import com.animalmanagementsystem.shelter.services.CageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CageServiceImpl implements CageService {

    private final CageRepository cageRepository;
    private final CageMapper cageMapper;
    private final EntityManager entityManager;
    private static final String CAGE_NOT_FOUND_MESSAGE = "Cage not found";

    public CageServiceImpl(CageRepository cageRepository, CageMapper cageMapper, EntityManager entityManager) {
        this.cageRepository = cageRepository;
        this.cageMapper = cageMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<CageDto> findCageByCriteria(CageSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CageEntity> criteriaQuery = criteriaBuilder.createQuery(CageEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<CageEntity> root = criteriaQuery.from(CageEntity.class);

        if (request.cageNumber() != null && !request.cageNumber().isBlank()) {
            Predicate cagePredicate = criteriaBuilder.like(root.get("cageNumber"), "%"
                    + request.cageNumber() + "%");
            predicates.add(cagePredicate);
        } else if (request.availability() != null && !request.availability().isBlank()) {
            Predicate availabilityPredicate = criteriaBuilder.like(root.get("availability"), "%"
                    + request.availability() + "%");
            predicates.add(availabilityPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<CageEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream()
                .map(cageMapper::mapEntityToDto)
                .toList();
    }

    @Override
    public CageDto createCage(CageDto cageDtoR) {
        CageEntity cageEntity = cageMapper.mapDtoToEntity(cageDtoR);
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
        Optional<CageEntity> optionalCageEntity = cageRepository.findById(cageDto.id());
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
