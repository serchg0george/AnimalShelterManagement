package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_animal")
public class AnimalEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "age")
    private Integer age;

    @OneToOne(mappedBy = "animal")
    CageEntity cage;

    @OneToMany(mappedBy = "status")
    List<HealthEntity> health;
}
