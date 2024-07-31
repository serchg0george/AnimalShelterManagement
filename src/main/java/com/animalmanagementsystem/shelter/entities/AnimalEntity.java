package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_animal")
public class AnimalEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    CageEntity cage;

    @OneToMany(mappedBy = "animals")
    List<UserAnimalEntity> users;

    @OneToMany(mappedBy = "status")
    List<HealthEntity> health;

    public AnimalEntity() {
    }

    public AnimalEntity(String name, String species, Integer age,
                        CageEntity cage, List<UserAnimalEntity> users, List<HealthEntity> health) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.cage = cage;
        this.users = users;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CageEntity getCage() {
        return cage;
    }

    public void setCage(CageEntity cage) {
        this.cage = cage;
    }

    public List<UserAnimalEntity> getAnimals() {
        return users;
    }

    public void setAnimals(List<UserAnimalEntity> users) {
        this.users = users;
    }

    public List<HealthEntity> getHealth() {
        return health;
    }

    public void setHealth(List<HealthEntity> health) {
        this.health = health;
    }
}
