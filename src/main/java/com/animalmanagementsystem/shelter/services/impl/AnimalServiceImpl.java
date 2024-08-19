package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.exceptions.NotFoundException;
import com.animalmanagementsystem.shelter.mappers.impl.AnimalMapperImpl;
import com.animalmanagementsystem.shelter.repositories.AnimalRepository;
import com.animalmanagementsystem.shelter.searchs.AnimalSearchRequest;
import com.animalmanagementsystem.shelter.services.AnimalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapperImpl animalMapper;
    private final EntityManager entityManager;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapperImpl animalMapper, EntityManager entityManager) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<AnimalDto> findAnimalByCriteria(final AnimalSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AnimalEntity> criteriaQuery = criteriaBuilder.createQuery(AnimalEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<AnimalEntity> root = criteriaQuery.from(AnimalEntity.class);

        if (request.age() != null && request.age() > 0) {
            Predicate agePredicate = criteriaBuilder.equal(root.get("age"), request.age());
            predicates.add(agePredicate);
        } else if (request.species() != null && !request.species().isBlank()) {
            Predicate speciesPredicate = criteriaBuilder.like(root.get("species"), "%"
                    + request.species() + "%");
            predicates.add(speciesPredicate);
        } else if (request.name() != null && !request.name().isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%"
                    + request.name() + "%");
            predicates.add(namePredicate);
        }
        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        TypedQuery<AnimalEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream().map(animalMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public AnimalDto createAnimal(AnimalDto animalDto) {
        AnimalEntity animalEntity = animalMapper.mapDtoToEntity(animalDto);
        AnimalEntity savedAnimal = animalRepository.save(animalEntity);
        return animalMapper.mapEntityToDto(savedAnimal);
    }

    @Override
    public AnimalDto getAnimalById(Long id) {
        return animalRepository.findById(id).map(animalMapper::mapEntityToDto).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<AnimalDto> getAllAnimals() {
        List<AnimalEntity> animalEntities = animalRepository.findAll();
        return animalEntities.stream().map(animalMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public AnimalDto updateAnimal(AnimalDto animalDto, Long id) {
        AnimalEntity animalEntity = animalMapper.mapDtoToEntity(animalDto);
        Optional<AnimalEntity> optionalAnimalEntity = animalRepository.findById(animalDto.id());
        if (optionalAnimalEntity.isEmpty()) {
            throw new NotFoundException(id);
        }
        AnimalEntity updatedAnimalEntity = optionalAnimalEntity.get();
        updatedAnimalEntity.setName(animalEntity.getName());
        updatedAnimalEntity.setAge(animalEntity.getAge());
        updatedAnimalEntity.setSpecies(animalEntity.getSpecies());
        updatedAnimalEntity.setHealth(animalEntity.getHealth());
        updatedAnimalEntity.setCage(animalEntity.getCage());
        updatedAnimalEntity.setId(id);
        return animalMapper.mapEntityToDto(animalRepository.save(updatedAnimalEntity));
    }

    @Override
    @Transactional
    public void deleteAnimal(Long id) {
        Optional<AnimalEntity> optionalAnimalEntity = animalRepository.findById(id);
        if (optionalAnimalEntity.isEmpty()) {
            throw new NotFoundException(id);
        }
        animalRepository.deleteById(id);
    }
}
