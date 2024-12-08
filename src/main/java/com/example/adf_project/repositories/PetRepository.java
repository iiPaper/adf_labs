package com.example.adf_project.repositories;

import com.example.adf_project.entities.Pet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    // Update Pet Details - Step 8.4
    @Transactional
    @Modifying
    @Query("UPDATE Pet p SET p.name = :name, p.age = :age, p.breed = :breed, p.animalType = :animalType WHERE p.id = :id")
    int updatePet(@Param("id") int id, @Param("name") String name, @Param("age") int age, @Param("breed") String breed, @Param("animalType") String animalType);


    // Delete Pet by ID - Step 8.5
    @Transactional
    @Modifying
    @Query("DELETE FROM Pet p WHERE p.id = :id")
    int deleteById(@Param("id") int id);


    // Delete Pet by Name - Step 8.6
    @Transactional
    @Modifying
    @Query("DELETE FROM Pet p WHERE LOWER(p.name) = LOWER(:name)")
    int deleteByName(@Param("name") String name);

    // Find Pets by Animal Type - Step 8.7
    List<Pet> findAllByAnimalType(String animalType);

    // Find Pets by Breed - Step 8.8
    List<Pet> findAllByBreed(String breed);

    // Get Name, Animal Type and Breed Only - Step 8.9
    @Query("SELECT p.name, p.animalType, p.breed from Pet p")
    List<Object[]> getNameAndAnimalTypeAndBreed();

    // Get Pet Statistics (Avg Age, Max Age) - Step 8.10
    @Query("SELECT AVG(p.age) AS avgAge, MAX(p.age) AS maxAge from Pet p")
    Object[] getPetStatistics();

}
