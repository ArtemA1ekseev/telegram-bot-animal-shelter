package com.skypro.telegrambotanimalshelter.repository;

import com.skypro.telegrambotanimalshelter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
