package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "t_health")
public class HealthEntity extends BaseEntity {

    @Column(name = "status")
    private String status;

    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne
    AnimalEntity animal;

    public HealthEntity() {
    }

    public HealthEntity(String status, Date updateDate, AnimalEntity animal) {
        this.status = status;
        this.updateDate = updateDate;
        this.animal = animal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }
}
