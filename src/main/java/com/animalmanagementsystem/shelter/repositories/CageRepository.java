package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.CageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CageRepository extends JpaRepository<CageEntity, Long> {
}
