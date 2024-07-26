package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class CageDto extends BaseDto {
    private String cageNumber;
    private String availability;
    AnimalDto animal;

    public CageDto() {
    }

    public CageDto(String cageNumber, String availability, AnimalDto animal) {
        this.cageNumber = cageNumber;
        this.availability = availability;
        this.animal = animal;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public AnimalDto getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDto animal) {
        this.animal = animal;
    }
}
