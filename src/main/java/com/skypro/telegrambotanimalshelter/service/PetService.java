package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.model.Pet;
import com.skypro.telegrambotanimalshelter.model.User;
import com.skypro.telegrambotanimalshelter.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service for working with pets
 * <br>
 * Properties: <b>petRepository</b>
 * @see PetRepository
 * @version 0.0.1
 * @author Bulat Bazarov
 */
@Service
public class PetService {

    private final PetRepository petRepository;

    /**
     * Constructor - creates a bean PetService in Spring framework with dependencies
     * @param petRepository
     */
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Method for adding pet. Takes parameters:
     * @param petKind
     * @param petBreed
     * @param petColor
     * @param petName
     * @param petAge
     */
    public void addPet(String petKind, String petBreed, String petColor, String petName, int petAge) {
        Pet pet = new Pet();
        pet.setPetKind(petKind);
        pet.setPetBreed(petBreed);
        pet.setPetColor(petColor);
        pet.setPetName(petName);
        pet.setPetAge(petAge);
        pet.setPetRecieptDate(LocalDateTime.now());
        petRepository.save(pet);
    }

    /**
     * Method for adopting pet. Takes parameters:
     * @param pet
     * @param user
     */
    public void adoptPet(Pet pet, User user) {
        if (user.isUserAbleToAdoptPet()) {
            pet.setUser(user);
        }
    }
}
