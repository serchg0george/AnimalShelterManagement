package com.animalmanagementsystem.shelter.exceptions;

public class UserRoleNotFoundException extends RuntimeException {
    public UserRoleNotFoundException(Long id) {
        super("UserRole with id " + id + " not found.");
    }
}
