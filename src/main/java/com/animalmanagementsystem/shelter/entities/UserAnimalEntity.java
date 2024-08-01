package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user_animal")
public class UserAnimalEntity extends BaseEntity {

    @ManyToOne
    UserEntity users;

    @ManyToOne
    AnimalEntity animals;

    public UserAnimalEntity() {
    }

    public UserAnimalEntity(UserEntity users, AnimalEntity animals) {
        this.users = users;
        this.animals = animals;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public AnimalEntity getAnimals() {
        return animals;
    }

    public void setAnimals(AnimalEntity animals) {
        this.animals = animals;
    }
}