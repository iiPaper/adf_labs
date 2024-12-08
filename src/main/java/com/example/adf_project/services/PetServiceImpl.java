package com.example.adf_project.services;

import com.example.adf_project.entities.Pet;
import com.example.adf_project.repositories.PetRepository;
import com.example.adf_project.services.exceptions.BadDataException;
import com.example.adf_project.services.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    @Override
    // Create Pet - Step 8.1
    public Pet createPet(Pet pet) throws BadDataException{
        if (pet.getName().isBlank()) {
            throw new BadDataException("Bad name value.");
        }
        if (pet.getBreed().isBlank()) {
            throw new BadDataException("Bad breed value.");
        }
        if (pet.getAge() < 0) {
            throw new BadDataException("Bad age value.");
        }
        if (pet.getAnimalType().isBlank()) {
            throw new BadDataException("Bad animal type value.");
        }

        return petRepository.save(pet);
    }

    @Override
    // Read All Pets - Step 8.2
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    // Read Pet by ID - Step 8.3
    public Pet getPetById(int id) throws NotFoundException {
        return petRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Pet not found with ID: " + id)
        );
    }

    @Transactional
    @Override
    // Update Pet Details - Step 8.4
    public Pet updatePet(int id, Pet updatedPet) throws NotFoundException {
        int rowsAffected = petRepository.updatePet(id, updatedPet.getName(), updatedPet.getAge(), updatedPet.getBreed(), updatedPet.getAnimalType());
        if (rowsAffected != 1)
            throw new NotFoundException("Pet not found with ID: " + id);
        return getPetById(id);
    }

    @Override
    // Delete Pet by ID - Step 8.5
    public void deletePetById(int id) throws NotFoundException {
        int rowsAffected = petRepository.deleteById(id);
        if (rowsAffected != 1)
            throw new NotFoundException("Pet not found with ID: " + id);
    }

    @Override
    // Delete Pets by Name - Step 8.6
    public void deletePetsByName(String name) throws NotFoundException {
        if (petRepository.deleteByName(name) == 0)
            throw new NotFoundException("Pet not found with name: " + name);
    }

    @Override
    // Find Pets by Animal Type - Step 8.7
    public List<Pet> findPetsByAnimalType(String animalType) throws NotFoundException {
        List<Pet> result = petRepository.findAllByAnimalType(animalType);
        if (result.isEmpty())
            throw new NotFoundException("Pet not found with animal type: "+ animalType);
        else return result;
    }

    @Override
    // Find Pets by Breed - Step 8.8
    public List<Pet> findPetsByBreed(String breed) throws NotFoundException {
        List<Pet> result = petRepository.findAllByBreed(breed);
        if (result.isEmpty())
            throw new NotFoundException("Pet not found with breed: " + breed);
        else return result;
    }

    @Override
    // Get Name, Animal Type and Breed Only - Step 8.9
    public List<Object[]> getNameAndAnimalTypeAndBreed() {
        return petRepository.getNameAndAnimalTypeAndBreed();
    }

    @Override
    // Get Pet Statistics - Step 8.10
    public Object[] getPetStatistics() {
        return petRepository.getPetStatistics();
    }
}
