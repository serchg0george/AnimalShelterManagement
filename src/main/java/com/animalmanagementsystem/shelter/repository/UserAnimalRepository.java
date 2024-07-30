package com.animalmanagementsystem.shelter.repository;

import com.animalmanagementsystem.shelter.entities.UserAnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnimalRepository extends JpaRepository<UserAnimalEntity, Long> {
}