package com.example.project.controllers;

import com.example.adf_project.dtos.HouseholdDTO;
import com.example.adf_project.dtos.PetDTO;
import com.example.adf_project.entities.Household;
import com.example.adf_project.entities.Pet;
import com.example.adf_project.services.HouseholdService;
import com.example.adf_project.services.PetService;
import com.example.adf_project.services.exceptions.BadDataException;
import com.example.adf_project.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphQLAPI {

    private final HouseholdService householdService;
    private final PetService petService;

    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    @QueryMapping
    public List<Pet> getPetsByAnimalType(@Argument String animalType) throws NotFoundException{
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household getHouseholdByEircode(@Argument String eircode) throws NotFoundException {
        return householdService.findHouseholdByEircode(eircode);
    }

    @QueryMapping
    public Pet getPetById(@Argument int id) throws NotFoundException {
        return petService.getPetById(id);
    }

    @MutationMapping
    @Secured(value = "ROLE_ADMIN")
    public Household createHousehold(@Valid @Argument("household") HouseholdDTO householdDTO) throws BadDataException {
        Household household = new Household(
                householdDTO.eircode(),
                householdDTO.numberOfOccupants(),
                householdDTO.maxNumberOfOccupants(),
                householdDTO.ownerOccupied(),
                null
        );
        return householdService.createHousehold(household);
    }

    @MutationMapping
    @Secured(value = "ROLE_ADMIN")
    public boolean deleteHouseholdByEircode(@Argument String eircode) throws NotFoundException {
        householdService.deleteHousehold(eircode);
        return true;
    }

    @MutationMapping
    @Secured(value = "ROLE_ADMIN")
    public Pet createPet(@Valid @Argument("pet") PetDTO petDTO) throws NotFoundException, BadDataException {
        Pet pet = new Pet(
                0,
                petDTO.name(),
                petDTO.animalType(),
                petDTO.breed(),
                petDTO.age(),
                null
        );
        return petService.createPet(pet);
    }

    @MutationMapping
    @Secured(value = "ROLE_ADMIN")
    public boolean deletePetById(@Argument int id) throws NotFoundException {
        petService.deletePetById(id);
        return true;
    }
}