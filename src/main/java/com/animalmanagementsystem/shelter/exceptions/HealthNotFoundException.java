package com.animalmanagementsystem.shelter.exceptions;

public class HealthNotFoundException extends RuntimeException {
    public HealthNotFoundException(Long id) {
        super("Health with id " + id + " not found.");
    }
}
