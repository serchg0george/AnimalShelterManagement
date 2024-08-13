package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_cage")
public class CageEntity extends BaseEntity {

    @Column(name = "cage_number")
    private String cageNumber;

    @Column(name = "availability")
    private String availability;


    public CageEntity() {
    }

    public CageEntity(String cageNumber, String availability) {
        this.cageNumber = cageNumber;
        this.availability = availability;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CageEntity that = (CageEntity) o;
        return getCageNumber().equals(that.getCageNumber()) && getAvailability().equals(that.getAvailability());
    }

    @Override
    public int hashCode() {
        int result = getCageNumber().hashCode();
        result = 31 * result + getAvailability().hashCode();
        return result;
    }
}
