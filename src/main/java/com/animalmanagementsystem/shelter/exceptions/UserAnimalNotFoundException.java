package com.animalmanagementsystem.shelter.exceptions;

public class UserAnimalNotFoundException extends RuntimeException {
    public UserAnimalNotFoundException(Long id) {
        super("UserAnimal with id " + id + " not found.");
    }
}
