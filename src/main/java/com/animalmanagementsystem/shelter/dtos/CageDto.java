package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class CageDto extends BaseDto {
    private String cageNumber;
    private String availability;

    public CageDto() {
    }

    public CageDto(String cageNumber, String availability) {
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

}
