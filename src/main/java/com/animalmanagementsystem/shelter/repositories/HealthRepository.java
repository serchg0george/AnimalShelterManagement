package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.HealthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends JpaRepository<HealthEntity, Long> {
}
