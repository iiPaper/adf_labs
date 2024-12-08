package com.example.adf_project.services;

import com.example.adf_project.entities.Household;
import com.example.adf_project.services.exceptions.NotFoundException;

import java.util.List;

public interface HouseholdService {

    Household findHouseholdByEircode(String eircode) throws NotFoundException;

    Household findHouseholdByEircodeWithPets(String eircode) throws NotFoundException;

    List<Household> findHouseholdsWithNoPets();

    List<Household> findAllHouseholds();

    Household createHousehold(Household household);

    void deleteHousehold(String eircode) throws NotFoundException;
}