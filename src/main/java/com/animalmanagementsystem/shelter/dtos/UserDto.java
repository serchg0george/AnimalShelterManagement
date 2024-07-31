package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

import java.util.List;

public class UserDto extends BaseDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    List<UserRoleDto> roles;
    List<UserAnimalDto> animals;


    public UserDto() {
    }

    public UserDto(String email, String password, String firstName, String lastName, String phoneNumber,
                   List<UserRoleDto> roles, List<UserAnimalDto> animals) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.animals = animals;
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

    public List<UserRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleDto> roles) {
        this.roles = roles;
    }

    public List<UserAnimalDto> getAnimals() {
        return animals;
    }

    public void setAnimals(List<UserAnimalDto> animals) {
        this.animals = animals;
    }
}
