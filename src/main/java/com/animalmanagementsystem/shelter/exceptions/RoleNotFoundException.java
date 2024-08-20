package com.animalmanagementsystem.shelter.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role with id " + id + " not found.");
    }
}
