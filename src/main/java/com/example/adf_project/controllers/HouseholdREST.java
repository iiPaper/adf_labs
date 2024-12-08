package com.example.adf_project.controllers;

import com.example.adf_project.dtos.HouseholdDTO;
import com.example.adf_project.entities.Household;
import com.example.adf_project.services.HouseholdService;
import com.example.adf_project.services.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdREST {

    private final HouseholdService householdService;

    public HouseholdREST(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    @GetMapping("/{eircode}")
    public Household getHousehold(@PathVariable String eircode) throws NotFoundException {
        return householdService.findHouseholdByEircode(eircode);
    }

    @GetMapping("/no-pets")
    public List<Household> getHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @PostMapping
    public Household createHousehold(@Valid @RequestBody HouseholdDTO householdDTO) {
        return householdService.createHousehold(new Household(
                householdDTO.eircode(),
                householdDTO.numberOfOccupants(),
                householdDTO.maxNumberOfOccupants(),
                householdDTO.ownerOccupied(),
                null
        ));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) throws NotFoundException {
        householdService.deleteHousehold(eircode);
        return ResponseEntity.noContent().build();
    }
}