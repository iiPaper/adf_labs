package com.example.adf_project.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PetDTO(
        @NotBlank(message = "Pet name cannot be blank") String name,
        @NotBlank(message = "Animal type cannot be blank") String animalType,
        @NotBlank(message = "Breed cannot be blank") String breed,
        @Positive(message = "Age must be positive") int age) {
}