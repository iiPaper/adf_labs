package com.example.adf_project.controllers;

import com.example.adf_project.dtos.PetDTO;
import com.example.adf_project.entities.Pet;
import com.example.adf_project.services.PetService;
import com.example.adf_project.services.exceptions.BadDataException;
import com.example.adf_project.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetREST {

    private final PetService petService;

    public PetREST(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPet(@PathVariable int id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @PostMapping
    public Pet createPet(@Valid @RequestBody PetDTO petDTO) throws NotFoundException, BadDataException {
        return petService.createPet(new Pet(0, petDTO.name(), petDTO.animalType(), petDTO.breed(), petDTO.age(), null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) throws NotFoundException {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/name")
    public Pet changePetName(@PathVariable int id, @RequestBody String newName) throws NotFoundException{
        Pet pet = petService.getPetById(id);
        pet.setName(newName);
        return petService.updatePet(id, pet);
    }
}