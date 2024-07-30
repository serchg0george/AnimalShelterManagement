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


    public UserRoleEntity() {
    }

    public UserRoleEntity(List<UserEntity> users, List<RoleEntity> roles) {
        this.users = users;
        this.roles = roles;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}