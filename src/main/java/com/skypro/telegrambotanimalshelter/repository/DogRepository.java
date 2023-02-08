package com.skypro.telegrambotanimalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skypro.telegrambotanimalshelter.model.Dog;

/**
 * Interface DogRepository.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
