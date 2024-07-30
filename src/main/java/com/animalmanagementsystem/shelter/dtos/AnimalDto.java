package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class AnimalDto extends BaseDto {
    private String name;
    private String species;
    private Integer age;
    CageDto cage;
    UserAnimalDto animal;

    public AnimalDto() {
    }

    public AnimalDto(String name, String species, Integer age, CageDto cage, UserAnimalDto animal) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.cage = cage;
        this.animal = animal;
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

    public UserAnimalDto getAnimal() {
        return animal;
    }

    public void setAnimal(UserAnimalDto animal) {
        this.animal = animal;
    }
}
