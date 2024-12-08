package com.example.adf_project.services;

import com.example.adf_project.entities.Pet;
import com.example.adf_project.services.exceptions.BadDataException;
import com.example.adf_project.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    // Create Pet - Step 8.1
    Pet createPet(Pet pet) throws NotFoundException, BadDataException;

    // Read All Pets - Step 8.2
    List<Pet> getAllPets();

    // Read Pet by ID - Step 8.3
    Pet getPetById(int id) throws NotFoundException;

    // Update Pet Details - Step 8.4
    Pet updatePet(int id, Pet updatedPet) throws NotFoundException;

    // Delete Pet by ID - Step 8.5
    void deletePetById(int id) throws NotFoundException;

    // Delete Pets by Name - Step 8.6
    void deletePetsByName(String name) throws NotFoundException;

    // Find Pets by Animal Type - Step 8.7
    List<Pet> findPetsByAnimalType(String animalType) throws NotFoundException;

    // Find Pets by Breed - Step 8.8
    List<Pet> findPetsByBreed(String breed) throws NotFoundException;

    // Get Name, Animal Type and Breed Only - Step 8.9
    List<Object[]> getNameAndAnimalTypeAndBreed();

    // Get Pet Statistics (Avg Age, Max Age) - Step 8.10
    Object[] getPetStatistics();
}
