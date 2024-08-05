package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "t_health")
public class HealthEntity extends BaseEntity {

    @Column(name = "status")
    private String status;

    @Column(name = "update_date")
    private Date updateDate;


    public HealthEntity() {
    }

    public HealthEntity(String status, Date updateDate) {
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
