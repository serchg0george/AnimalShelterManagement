package com.animalmanagementsystem.shelter.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CageDto(Long id,
                      @NotBlank @Size(max = 20) String cageNumber,
                      @NotBlank String availability) {

}
