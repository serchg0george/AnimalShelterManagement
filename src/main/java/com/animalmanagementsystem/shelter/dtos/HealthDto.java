package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.Date;

public class HealthDto extends BaseDto {

    private String status;
    private Date updateDate;
    AnimalDto animal;

    public HealthDto() {
    }

    public HealthDto(String status, Date updateDate, AnimalDto animal) {
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

    public AnimalDto getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalDto animal) {
        this.animal = animal;
    }
}
