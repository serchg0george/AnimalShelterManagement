package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.HealthDto;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.exceptions.HealthNotFoundException;
import com.animalmanagementsystem.shelter.mappers.impl.HealthMapperImpl;
import com.animalmanagementsystem.shelter.repositories.HealthRepository;
import com.animalmanagementsystem.shelter.searchs.HealthSearchRequest;
import com.animalmanagementsystem.shelter.services.impl.HealthServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HealthServiceTest {

    @Mock
    private HealthRepository healthRepository;

    @Mock
    private HealthMapperImpl healthMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private HealthServiceImpl healthService;

    private HealthEntity healthEntity;
    private HealthDto healthDto;

    @BeforeEach
    void setUp() {
        healthEntity = new HealthEntity();
        healthEntity.setId(1L);
        healthEntity.setStatus("Healthy");
        healthEntity.setUpdateDate(Date.valueOf(LocalDate.now()));

        healthDto = new HealthDto(1L, "Healthy", Date.valueOf(LocalDate.now()));
    }

    @Test
    void testCreateHealth() {
        when(healthMapper.mapDtoToEntity(any(HealthDto.class))).thenReturn(healthEntity);
        when(healthRepository.save(any(HealthEntity.class))).thenReturn(healthEntity);
        when(healthMapper.mapEntityToDto(any(HealthEntity.class))).thenReturn(healthDto);

        HealthDto createdHealth = healthService.createHealth(healthDto);

        assertNotNull(createdHealth);
        assertEquals(healthDto.id(), createdHealth.id());
        verify(healthRepository, times(1)).save(any(HealthEntity.class));
    }

    @Test
    void testGetHealthById_HealthExists() {
        when(healthRepository.findById(1L)).thenReturn(Optional.of(healthEntity));
        when(healthMapper.mapEntityToDto(any(HealthEntity.class))).thenReturn(healthDto);

        HealthDto foundHealth = healthService.getHealthById(1L);

        assertNotNull(foundHealth);
        assertEquals(1L, foundHealth.id());
        verify(healthRepository, times(1)).findById(1L);
    }

    @Test
    void testGetHealthById_HealthNotFound() {
        when(healthRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HealthNotFoundException.class, () -> healthService.getHealthById(1L));
    }

    @Test
    void testGetAllHealths() {
        List<HealthEntity> healthEntities = List.of(healthEntity);
        when(healthRepository.findAll()).thenReturn(healthEntities);
        when(healthMapper.mapEntityToDto(any(HealthEntity.class))).thenReturn(healthDto);

        List<HealthDto> healthDtos = healthService.getAllHealths();

        assertNotNull(healthDtos);
        assertEquals(1, healthDtos.size());
        verify(healthRepository, times(1)).findAll();
    }

    @Test
    void testUpdateHealth_HealthExists() {
        when(healthRepository.findById(1L)).thenReturn(Optional.of(healthEntity));
        when(healthMapper.mapDtoToEntity(any(HealthDto.class))).thenReturn(healthEntity);
        when(healthRepository.save(any(HealthEntity.class))).thenReturn(healthEntity);
        when(healthMapper.mapEntityToDto(any(HealthEntity.class))).thenReturn(healthDto);

        HealthDto updatedHealth = healthService.updateHealth(healthDto, 1L);

        assertNotNull(updatedHealth);
        assertEquals(1L, updatedHealth.id());
        verify(healthRepository, times(1)).findById(1L);
        verify(healthRepository, times(1)).save(any(HealthEntity.class));
    }

    @Test
    void testUpdateHealth_HealthNotFound() {
        when(healthRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HealthNotFoundException.class, () -> healthService.updateHealth(healthDto, 1L));
    }

    @Test
    void testDeleteHealth_HealthExists() {
        when(healthRepository.findById(1L)).thenReturn(Optional.of(healthEntity));

        healthService.deleteHealth(1L);

        verify(healthRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteHealth_HealthNotFound() {
        when(healthRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HealthNotFoundException.class, () -> healthService.deleteHealth(1L));
    }

    @Test
    void testFindHealthByCriteria() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<HealthEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<HealthEntity> root = mock(Root.class);
        TypedQuery<HealthEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(HealthEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(HealthEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(healthEntity));
        when(healthMapper.mapEntityToDto(any(HealthEntity.class))).thenReturn(healthDto);

        HealthSearchRequest request = new HealthSearchRequest("Healthy", Date.valueOf(LocalDate.now()));
        List<HealthDto> foundHealths = healthService.findHealthByCriteria(request);

        assertNotNull(foundHealths);
        assertEquals(1, foundHealths.size());
        verify(entityManager, times(1)).createQuery(criteriaQuery);
    }
}
