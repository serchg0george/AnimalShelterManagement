package com.animalmanagementsystem.shelter.exceptions;

public class CageNotFoundException extends RuntimeException {
    public CageNotFoundException(Long id) {
        super("Cage with id " + id + " not found.");
    }
}
