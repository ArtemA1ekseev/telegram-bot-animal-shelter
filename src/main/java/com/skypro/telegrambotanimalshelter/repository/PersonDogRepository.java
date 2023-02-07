package com.skypro.telegrambotanimalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skypro.telegrambotanimalshelter.model.PersonDog;
import java.util.Set;

/**
 * Interface PersonDogRepository.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@Repository
public interface PersonDogRepository extends JpaRepository<PersonDog, Long> {

    Set<PersonDog> findByChatId(Long chatId);

}