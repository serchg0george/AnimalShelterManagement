package com.animalmanagementsystem.shelter.dtos;

import java.util.List;

public record AnimalDto(Long id,
                        String name,
                        String species,
                        Integer age,
                        CageDto cage,
                        List<UserAnimalDto> users,
                        HealthDto health) {
}
