package com.animalmanagementsystem.shelter.dtos;

import com.animalmanagementsystem.shelter.dtos.base.BaseDto;

public class UserDto extends BaseDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    UserRoleDto role;
    UserAnimalDto animal;


    public UserDto() {
    }

    public UserDto(String email, String password, String firstName,
                   String lastName, String phoneNumber, UserRoleDto role, UserAnimalDto animal) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.animal = animal;
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

    public UserRoleDto getRole() {
        return role;
    }

    public void setRole(UserRoleDto role) {
        this.role = role;
    }

    public UserAnimalDto getAnimal() {
        return animal;
    }

    public void setAnimal(UserAnimalDto animal) {
        this.animal = animal;
    }
}
