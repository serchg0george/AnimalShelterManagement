package com.animalmanagementsystem.shelter.searchs;

public record UserSearchRequest(String email,
                                String firstName,
                                String lastName,
                                String phoneNumber) {
}
