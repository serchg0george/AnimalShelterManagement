package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.List;

public class AnimalDto extends BaseDto {
    private String name;
    private String species;
    private Integer age;
    CageDto cage;
    List<UserAnimalDto> users;
    HealthDto health;

    public AnimalDto() {
    }

    public AnimalDto(String name, String species, Integer age, CageDto cage,
                     List<UserAnimalDto> users, HealthDto health) {
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

    public CageDto getCage() {
        return cage;
    }

    public void setCage(CageDto cage) {
        this.cage = cage;
    }

    public List<UserAnimalDto> getAnimals() {
        return users;
    }

    public void setAnimals(List<UserAnimalDto> users) {
        this.users = users;
    }

    public HealthDto getHealth() {
        return health;
    }

    public void setHealth(HealthDto health) {
        this.health = health;
    }
}
