package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "t_user_animal")
public class UserAnimalEntity extends BaseEntity {
    @OneToMany(mappedBy = "email")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "name")
    private List<AnimalEntity> animals;

    public UserAnimalEntity() {
    }

    public UserAnimalEntity(List<UserEntity> users, List<AnimalEntity> animals) {
        this.users = users;
        this.animals = animals;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<AnimalEntity> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalEntity> animals) {
        this.animals = animals;
    }
}