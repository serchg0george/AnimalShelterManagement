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

}
