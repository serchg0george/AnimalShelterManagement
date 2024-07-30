package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "t_cage")
public class CageEntity extends BaseEntity {

    @Column(name = "cage_number")
    private String cageNumber;

    @Column(name = "availability")
    private String availability;

    @OneToOne
    @JoinColumn(name = "animal_id")
    AnimalEntity animal;

    public CageEntity() {
    }

    public CageEntity(String cageNumber, String availability, AnimalEntity animal) {
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

    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }
}
