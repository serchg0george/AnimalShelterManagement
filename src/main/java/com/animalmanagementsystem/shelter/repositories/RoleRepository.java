package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
