package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "t_user_role")
public class UserRoleEntity extends BaseEntity {

    @OneToMany(mappedBy = "email")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "name")
    private List<RoleEntity> roles;

}