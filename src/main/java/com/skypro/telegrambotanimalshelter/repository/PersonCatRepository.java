package com.skypro.telegrambotanimalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skypro.telegrambotanimalshelter.model.PersonCat;
import java.util.Set;

/**
 * Interface PersonCatRepository.
 * @author Artem Alekseev
 * @version 1.0.0
 */
public interface PersonCatRepository extends JpaRepository<PersonCat, Long> {

    Set<PersonCat> findByChatId(Long chatId);

}
