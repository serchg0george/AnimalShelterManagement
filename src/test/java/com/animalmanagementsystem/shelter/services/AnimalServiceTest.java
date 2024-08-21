package com.animalmanagementsystem.shelter.services;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.entities.CageEntity;
import com.animalmanagementsystem.shelter.entities.HealthEntity;
import com.animalmanagementsystem.shelter.exceptions.AnimalNotFoundException;
import com.animalmanagementsystem.shelter.mappers.CageMapper;
import com.animalmanagementsystem.shelter.mappers.impl.AnimalMapperImpl;
import com.animalmanagementsystem.shelter.mappers.impl.HealthMapperImpl;
import com.animalmanagementsystem.shelter.repositories.AnimalRepository;
import com.animalmanagementsystem.shelter.services.impl.AnimalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AnimalMapperImpl animalMapper;

    @Mock
    private CageMapper cageMapper;

    @Mock
    private HealthMapperImpl healthMapper;

    @InjectMocks
    private AnimalServiceImpl animalService;

    private AnimalDto animalDto;
    private AnimalEntity animalEntity;

    @BeforeEach
    void setUp() {
        CageEntity cageEntity = new CageEntity("Cage1", "Available");
        HealthEntity healthEntity = new HealthEntity("Healthy", new Date());
        animalDto = new AnimalDto(1L, "Lion", "Mammal", 5, cageMapper.mapEntityToDto(cageEntity), null, healthMapper.mapEntityToDto(healthEntity));
        animalEntity = new AnimalEntity("Lion", "Mammal", 5, cageEntity, null, healthEntity);
    }

    @Test
    void testCreateAnimal() {
        when(animalMapper.mapDtoToEntity(animalDto)).thenReturn(animalEntity);
        when(animalRepository.save(animalEntity)).thenReturn(animalEntity);
        when(animalMapper.mapEntityToDto(animalEntity)).thenReturn(animalDto);

        AnimalDto result = animalService.createAnimal(animalDto);

        assertEquals(animalDto, result);
        verify(animalRepository).save(animalEntity);
    }

    @Test
    void testGetAnimalById() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animalEntity));
        when(animalMapper.mapEntityToDto(animalEntity)).thenReturn(animalDto);

        AnimalDto result = animalService.getAnimalById(1L);

        assertEquals(animalDto, result);
        verify(animalRepository).findById(1L);
    }

    @Test
    void testGetAnimalByIdNotFound() {
        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        AnimalNotFoundException exception = assertThrows(AnimalNotFoundException.class, () -> {
            animalService.getAnimalById(1L);
        });

        assertEquals("Animal with id 1 not found.", exception.getMessage());
    }

    @Test
    void testGetAllAnimals() {
        List<AnimalEntity> animalEntities = Arrays.asList(animalEntity);
        when(animalRepository.findAll()).thenReturn(animalEntities);
        when(animalMapper.mapEntityToDto(animalEntity)).thenReturn(animalDto);

        List<AnimalDto> result = animalService.getAllAnimals();

        assertEquals(1, result.size());
        assertEquals(animalDto, result.get(0));
        verify(animalRepository).findAll();
    }

    @Test
    void testUpdateAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animalEntity));
        when(animalMapper.mapDtoToEntity(animalDto)).thenReturn(animalEntity);
        when(animalRepository.save(animalEntity)).thenReturn(animalEntity);
        when(animalMapper.mapEntityToDto(animalEntity)).thenReturn(animalDto);

        AnimalDto result = animalService.updateAnimal(animalDto, 1L);

        assertEquals(animalDto, result);
        verify(animalRepository).findById(1L);
        verify(animalRepository).save(animalEntity);
    }

    @Test
    void testUpdateAnimalNotFound() {
        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        AnimalNotFoundException exception = assertThrows(AnimalNotFoundException.class, () -> {
            animalService.updateAnimal(animalDto, 1L);
        });

        assertEquals("Animal with id 1 not found.", exception.getMessage());
    }

    @Test
    void testDeleteAnimal() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animalEntity));

        animalService.deleteAnimal(1L);

        verify(animalRepository).findById(1L);
        verify(animalRepository).deleteById(1L);
    }

    @Test
    void testDeleteAnimalNotFound() {
        when(animalRepository.findById(1L)).thenReturn(Optional.empty());

        AnimalNotFoundException exception = assertThrows(AnimalNotFoundException.class, () -> {
            animalService.deleteAnimal(1L);
        });

        assertEquals("Animal with id 1 not found.", exception.getMessage());
    }
}
