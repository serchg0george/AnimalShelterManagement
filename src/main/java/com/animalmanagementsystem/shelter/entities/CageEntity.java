package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "t_cage")
public class CageEntity extends BaseEntity {

    @Column(name = "cage_number")
    private String cageNumber;

    @Column(name = "availability")
    private String availability;

    @OneToMany(mappedBy = "cage")
    List<AnimalEntity> animals;

    public CageEntity() {
    }

    public CageEntity(String cageNumber, String availability, List<AnimalEntity> animals) {
        this.cageNumber = cageNumber;
        this.availability = availability;
        this.animals = animals;
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

    public List<AnimalEntity> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalEntity> animals) {
        this.animals = animals;
    }
}
