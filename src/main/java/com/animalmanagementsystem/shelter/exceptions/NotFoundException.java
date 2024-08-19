package com.animalmanagementsystem.shelter.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Entity with id " + id + " not found.");
    }
}
