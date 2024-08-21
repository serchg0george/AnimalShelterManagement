package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.CageDto;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.exceptions.CageNotFoundException;
import com.animalmanagementsystem.shelter.mappers.impl.CageMapperImpl;
import com.animalmanagementsystem.shelter.repositories.CageRepository;
import com.animalmanagementsystem.shelter.searchs.CageSearchRequest;
import com.animalmanagementsystem.shelter.services.impl.CageServiceImpl;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CageServiceTest {

    @Mock
    private CageRepository cageRepository;

    @Mock
    private CageMapperImpl cageMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CageServiceImpl cageService;

    private CageEntity cageEntity;
    private CageDto cageDto;

    @BeforeEach
    void setUp() {
        cageEntity = new CageEntity();
        cageEntity.setId(1L);
        cageEntity.setCageNumber("Cage-1");
        cageEntity.setAvailability("Available");

        cageDto = new CageDto(1L, "Cage-1", "Available");
    }

    @Test
    void testCreateCage() {
        when(cageMapper.mapDtoToEntity(any(CageDto.class))).thenReturn(cageEntity);
        when(cageRepository.save(any(CageEntity.class))).thenReturn(cageEntity);
        when(cageMapper.mapEntityToDto(any(CageEntity.class))).thenReturn(cageDto);

        CageDto createdCage = cageService.createCage(cageDto);

        assertNotNull(createdCage);
        assertEquals(cageDto.id(), createdCage.id());
        verify(cageRepository, times(1)).save(any(CageEntity.class));
    }

    @Test
    void testGetCageById_CageExists() {
        when(cageRepository.findById(1L)).thenReturn(Optional.of(cageEntity));
        when(cageMapper.mapEntityToDto(any(CageEntity.class))).thenReturn(cageDto);

        CageDto foundCage = cageService.getCageById(1L);

        assertNotNull(foundCage);
        assertEquals(1L, foundCage.id());
        verify(cageRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCageById_CageNotFound() {
        when(cageRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CageNotFoundException.class, () -> cageService.getCageById(1L));
    }

    @Test
    void testGetAllCages() {
        List<CageEntity> cageEntities = List.of(cageEntity);
        when(cageRepository.findAll()).thenReturn(cageEntities);
        when(cageMapper.mapEntityToDto(any(CageEntity.class))).thenReturn(cageDto);

        List<CageDto> cageDtos = cageService.getAllCages();

        assertNotNull(cageDtos);
        assertEquals(1, cageDtos.size());
        verify(cageRepository, times(1)).findAll();
    }

    @Test
    void testUpdateCage_CageExists() {
        when(cageRepository.findById(1L)).thenReturn(Optional.of(cageEntity));
        when(cageMapper.mapDtoToEntity(any(CageDto.class))).thenReturn(cageEntity);
        when(cageRepository.save(any(CageEntity.class))).thenReturn(cageEntity);
        when(cageMapper.mapEntityToDto(any(CageEntity.class))).thenReturn(cageDto);

        CageDto updatedCage = cageService.updateCage(cageDto, 1L);

        assertNotNull(updatedCage);
        assertEquals(1L, updatedCage.id());
        verify(cageRepository, times(1)).findById(1L);
        verify(cageRepository, times(1)).save(any(CageEntity.class));
    }

    @Test
    void testUpdateCage_CageNotFound() {
        when(cageRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CageNotFoundException.class, () -> cageService.updateCage(cageDto, 1L));
    }

    @Test
    void testDeleteCage_CageExists() {
        when(cageRepository.findById(1L)).thenReturn(Optional.of(cageEntity));

        cageService.deleteCage(1L);

        verify(cageRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCage_CageNotFound() {
        when(cageRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CageNotFoundException.class, () -> cageService.deleteCage(1L));
    }

    @Test
    void testFindCageByCriteria() {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<CageEntity> criteriaQuery = mock(CriteriaQuery.class);
        Root<CageEntity> root = mock(Root.class);
        TypedQuery<CageEntity> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(CageEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(CageEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(cageEntity));
        when(cageMapper.mapEntityToDto(any(CageEntity.class))).thenReturn(cageDto);

        CageSearchRequest request = new CageSearchRequest("Cage-1", null);
        List<CageDto> foundCages = cageService.findCageByCriteria(request);

        assertNotNull(foundCages);
        assertEquals(1, foundCages.size());
        verify(entityManager, times(1)).createQuery(criteriaQuery);
    }
}
