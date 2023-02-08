package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.repository.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.skypro.telegrambotanimalshelter.exceptions.DogNotFoundException;
import com.skypro.telegrambotanimalshelter.model.Dog;
import com.skypro.telegrambotanimalshelter.repository.DogRepository;

import java.util.Collection;

/**
 * Class of DogService.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@Service
public class DogService {

    private final DogRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(DogService.class);

    public DogService(DogRepository dogRepository) {
        this.repository = dogRepository;
    }

    /**
     * Method to get a dog by id.
     * @param id
     * @return {@link DogRepository#findById(Object)}
     * @see DogService
     * @exception DogNotFoundException
     */
    public Dog getById(Long id) {
        logger.info("Was invoked method to get a dog by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(DogNotFoundException::new);
    }

    /**
     * Method to create a dog.
     * @param dog
     * @return {@link DogRepository#save(Object)}
     * @see DogService
     */
    public Dog create(Dog dog) {
        logger.info("Was invoked method to create a dog");
        return this.repository.save(dog);
    }

    /**
     * Method to update a dog.
     * @param dog
     * @return {@link DogRepository#save(Object)}
     * @see DogService
     * @exception DogNotFoundException
     */
    public Dog update(Dog dog) {
        logger.info("Was invoked method to update a dog");
        if (dog.getId() != null) {
            if (getById(dog.getId()) != null) {
                return this.repository.save(dog);
            }
        }
        throw new DogNotFoundException();
    }

    /**
     * Method to get all dogs.
     * @return {@link DogRepository#findAll()}
     * @see DogService
     */
    public Collection<Dog> getAll() {
        logger.info("Was invoked method to get all dogs");

        return this.repository.findAll();
    }

    /**
     * Method to remove a cat by id.
     * @param id
     */
    public void removeById(Long id) {
        logger.info("Was invoked method to remove a cat by id={}", id);

        this.repository.deleteById(id);
    }
}
