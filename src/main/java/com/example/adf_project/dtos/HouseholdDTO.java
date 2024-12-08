package com.example.adf_project.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HouseholdDTO(
        @NotBlank(message = "Eircode cannot be blank") String eircode,
        @Min(value = 0, message = "Number of occupants cannot be negative") int numberOfOccupants,
        @Min(value = 1, message = "Maximum number of occupants must be at least 1")
        @Max(value = 100, message = "Maximum number of occupants is too large") int maxNumberOfOccupants,
        @NotNull(message = "Owner occupied must be true or false") Boolean ownerOccupied) {
}