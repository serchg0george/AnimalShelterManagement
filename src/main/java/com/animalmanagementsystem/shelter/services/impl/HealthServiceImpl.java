package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.exceptions.HealthNotFoundException;
import com.animalmanagementsystem.shelter.mappers.HealthMapper;
import com.animalmanagementsystem.shelter.repositories.HealthRepository;
import com.animalmanagementsystem.shelter.searchs.HealthSearchRequest;
import com.animalmanagementsystem.shelter.services.HealthService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HealthServiceImpl implements HealthService {

    private final HealthRepository healthRepository;
    private final HealthMapper healthMapper;
    private final EntityManager entityManager;

    public HealthServiceImpl(HealthRepository healthRepository, HealthMapper healthMapper, EntityManager entityManager) {
        this.healthRepository = healthRepository;
        this.healthMapper = healthMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<HealthDto> findHealthByCriteria(final HealthSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HealthEntity> criteriaQuery = criteriaBuilder.createQuery(HealthEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<HealthEntity> healthEntityRoot = criteriaQuery.from(HealthEntity.class);

        if (request.status() != null && !request.status().isBlank()) {
            Predicate statusPredicate = criteriaBuilder.like(healthEntityRoot.get("status"), "%" + request.status() + "%");
            predicates.add(statusPredicate);
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<HealthEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream().map(healthMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public HealthDto createHealth(HealthDto healthDto) {
        HealthEntity healthEntity = healthMapper.mapDtoToEntity(healthDto);
        healthEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
        HealthEntity savedHealth = healthRepository.save(healthEntity);
        return healthMapper.mapEntityToDto(savedHealth);
    }

    @Override
    public HealthDto getHealthById(Long id) {
        return healthRepository.findById(id).map(healthMapper::mapEntityToDto).orElseThrow(() -> new HealthNotFoundException(id));
    }

    @Override
    public List<HealthDto> getAllHealths() {
        List<HealthEntity> healthEntities = healthRepository.findAll();
        return healthEntities.stream().map(healthMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public HealthDto updateHealth(HealthDto healthDto, Long id) {
        HealthEntity healthEntity = healthMapper.mapDtoToEntity(healthDto);
        Optional<HealthEntity> optionalHealthEntity = healthRepository.findById(healthDto.id());

        HealthEntity updatedHealthEntity = optionalHealthEntity.orElseThrow(() -> new HealthNotFoundException(id));

        updatedHealthEntity.setStatus(healthEntity.getStatus());
        updatedHealthEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
        updatedHealthEntity.setId(id);
        return healthMapper.mapEntityToDto(healthRepository.save(updatedHealthEntity));
    }

    @Override
    @Transactional
    public void deleteHealth(Long id) {
        Optional<HealthEntity> optionalHealthEntity = healthRepository.findById(id);

        optionalHealthEntity.orElseThrow(() -> new HealthNotFoundException(id));

        healthRepository.deleteById(id);
    }
}
