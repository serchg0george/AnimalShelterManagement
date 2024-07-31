package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.Date;

public class HealthDto extends BaseDto {

    private String status;
    private Date updateDate;

    public HealthDto() {
    }

    public HealthDto(String status, Date updateDate) {
        this.status = status;
        this.updateDate = updateDate;
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
}
