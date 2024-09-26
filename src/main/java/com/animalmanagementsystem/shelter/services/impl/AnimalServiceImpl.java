package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.AnimalDto;
import com.animalmanagementsystem.shelter.entities.AnimalEntity;
import com.animalmanagementsystem.shelter.exceptions.AnimalNotFoundException;
import com.animalmanagementsystem.shelter.mappers.AnimalMapper;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final EntityManager entityManager;
    public static final String NAME = "name";

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper, EntityManager entityManager) {
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

        if (request.query() != null && !request.query().isBlank()) {
            String query = "%" + request.query() + "%";
            Predicate namePredicate = criteriaBuilder.like(root.get(NAME), query);
            Predicate speciesPredicate = criteriaBuilder.like(root.get("species"), query);

            predicates.add(criteriaBuilder.or(namePredicate, speciesPredicate));
        }

        criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(NAME)));

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
        return animalRepository.findById(id).map(animalMapper::mapEntityToDto).orElseThrow(() -> new AnimalNotFoundException(id));
    }

    @Override
    public List<AnimalDto> getAllAnimals() {
        List<AnimalEntity> animalEntities = animalRepository.findAll(Sort.by(Sort.Direction.ASC, NAME));
        return animalEntities.stream().map(animalMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional
    public AnimalDto updateAnimal(AnimalDto animalDto, Long id) {
        AnimalEntity animalEntity = animalMapper.mapDtoToEntity(animalDto);
        Optional<AnimalEntity> optionalAnimalEntity = animalRepository.findById(animalDto.id());

        AnimalEntity updatedAnimalEntity = optionalAnimalEntity.orElseThrow(() -> new AnimalNotFoundException(id));

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
            throw new AnimalNotFoundException(id);
        }

        animalRepository.deleteById(id);
    }
}
