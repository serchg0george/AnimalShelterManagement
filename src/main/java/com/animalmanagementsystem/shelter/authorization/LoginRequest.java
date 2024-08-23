package com.animalmanagementsystem.shelter.authorization;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull String email,
                           @NotNull String password) {
}
