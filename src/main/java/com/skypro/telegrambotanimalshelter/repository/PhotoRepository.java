package com.skypro.telegrambotanimalshelter.repository;

import com.skypro.telegrambotanimalshelter.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findByPetId (Long PetId);

}
