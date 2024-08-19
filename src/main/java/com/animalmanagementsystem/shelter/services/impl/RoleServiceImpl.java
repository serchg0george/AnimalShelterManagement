package com.animalmanagementsystem.shelter.services.impl;

import com.animalmanagementsystem.shelter.dtos.RoleDto;
import com.animalmanagementsystem.shelter.entities.RoleEntity;
import com.animalmanagementsystem.shelter.mappers.impl.RoleMapperImpl;
import com.animalmanagementsystem.shelter.repositories.RoleRepository;
import com.animalmanagementsystem.shelter.searchs.RoleSearchRequest;
import com.animalmanagementsystem.shelter.services.RoleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapperImpl roleMapper;
    private final EntityManager entityManager;
    private static final String ROLE_NOT_FOUND_MESSAGE = "Role not found";

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapperImpl roleMapper, EntityManager entityManager) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<RoleDto> findRoleByCriteria(final RoleSearchRequest request) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoleEntity> criteriaQuery = criteriaBuilder.createQuery(RoleEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<RoleEntity> roleEntityRoot = criteriaQuery.from(RoleEntity.class);

        if (request.name() != null && !request.name().isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(roleEntityRoot.get("name"), "%"
                    + request.name() + "%");
            predicates.add(namePredicate);
        } else if (request.description() != null && !request.description().isBlank()) {
            Predicate descriptionPredicate = criteriaBuilder.like(roleEntityRoot.get("description"), "%"
                    + request.description() + "%");
            predicates.add(descriptionPredicate);
        }

        criteriaQuery.where(
                predicates.toArray(new Predicate[0])
        );

        TypedQuery<RoleEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream()
                .map(roleMapper::mapEntityToDto)
                .toList();
    }

    @Override
    @Transactional
    public RoleDto createRole(RoleDto roleDto) {
        RoleEntity roleEntity = roleMapper.mapDtoToEntity(roleDto);
        RoleEntity savedRole = roleRepository.save(roleEntity);
        return roleMapper.mapEntityToDto(savedRole);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream()
                .map(roleMapper::mapEntityToDto)
                .toList();
    }

    @Override
    @Transactional
    public RoleDto updateRole(RoleDto roleDto, Long id) {
        RoleEntity roleEntity = roleMapper.mapDtoToEntity(roleDto);
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(roleDto.id());
        if (optionalRoleEntity.isEmpty()) {
            throw new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE);
        }

        RoleEntity updatedRoleEntity = optionalRoleEntity.get();

        updatedRoleEntity.setId(id);
        updatedRoleEntity.setName(roleEntity.getName());
        updatedRoleEntity.setDescription(roleEntity.getDescription());
        return roleMapper.mapEntityToDto(roleRepository.save(updatedRoleEntity));
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(id);
        if (optionalRoleEntity.isEmpty()) {
            throw new EntityNotFoundException(ROLE_NOT_FOUND_MESSAGE);
        }
        roleRepository.deleteById(id);
    }
}
