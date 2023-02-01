package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.model.Pet;
import com.skypro.telegrambotanimalshelter.model.User;
import com.skypro.telegrambotanimalshelter.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public void addPet(String petKind, String breed, String petColor, String petName, int petAge) {
        Pet pet = new Pet();
        pet.setPetKind(petKind);
        pet.setBreed(breed);
        pet.setPetColor(petColor);
        pet.setPetName(petName);
        pet.setPetAge(petAge);
        pet.setRecieptDate(LocalDateTime.now());
        repository.save(pet);
    }

    public void adoptPet(Pet pet, User user) {
        if (user.isUserAbleToAdoptPet()) {
            pet.setUser(user);
        }
    }
}
