package com.animalmanagementsystem.shelter.entities;

import com.animalmanagementsystem.shelter.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE},
            mappedBy = "users", fetch = FetchType.LAZY)
    private List<UserAnimalEntity> userAnimalEntity;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE},
            mappedBy = "users", fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRoleEntity;

    public UserEntity() {
    }

    public UserEntity(String email, String password, String firstName,
                      String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<UserAnimalEntity> getUserAnimalEntity() {
        return userAnimalEntity;
    }

    public void setUserAnimalEntity(List<UserAnimalEntity> userAnimalEntity) {
        this.userAnimalEntity = userAnimalEntity;
    }

    public List<UserRoleEntity> getUserRoleEntity() {
        return userRoleEntity;
    }

    public void setUserRoleEntity(List<UserRoleEntity> userRoleEntity) {
        this.userRoleEntity = userRoleEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;
        return getEmail().equals(that.getEmail()) && getPassword().equals(that.getPassword()) && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getPhoneNumber().equals(that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int result = getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }
}