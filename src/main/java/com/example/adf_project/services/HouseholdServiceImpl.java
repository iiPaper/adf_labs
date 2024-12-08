package com.example.adf_project.services;

import com.example.adf_project.entities.Household;
import com.example.adf_project.repositories.HouseholdRepository;
import com.example.adf_project.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public Household findHouseholdByEircode(String eircode) throws NotFoundException {
        return householdRepository.findById(eircode).orElseThrow(() ->
                new NotFoundException("Household not found with Eircode: " + eircode));
    }

    @Override
    public Household findHouseholdByEircodeWithPets(String eircode) throws NotFoundException {
        Household household = householdRepository.findByEircodeWithPets(eircode);
        if (household == null)
            throw new NotFoundException("Household not found with Eircode: " + eircode);
        return household;
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public void deleteHousehold(String eircode) throws NotFoundException {
        if (!householdRepository.existsById(eircode)) {
            throw new NotFoundException("Household not found with eircode: " + eircode);
        }
        householdRepository.deleteById(eircode);
    }
}