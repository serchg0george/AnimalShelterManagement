package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user_role")
public class UserRoleEntity extends BaseEntity {

    @ManyToOne
    UserEntity users;

    @ManyToOne
    RoleEntity roles;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserEntity users, RoleEntity roles) {
        this.users = users;
        this.roles = roles;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public RoleEntity getRoles() {
        return roles;
    }

    public void setRoles(RoleEntity roles) {
        this.roles = roles;
    }
}