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
}
