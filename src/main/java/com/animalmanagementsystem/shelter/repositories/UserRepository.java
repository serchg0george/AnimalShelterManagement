package com.animalmanagementsystem.shelter.repositories;

import com.animalmanagementsystem.shelter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
