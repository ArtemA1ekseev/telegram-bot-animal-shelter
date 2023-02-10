package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.exceptions.DogNotFoundException;
import com.skypro.telegrambotanimalshelter.model.Dog;
import com.skypro.telegrambotanimalshelter.repository.DogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DogServiceTest {

    @Mock
    private DogRepository dogRepositoryMock;

    @InjectMocks
    private DogService dogService;

    @Test
    public void getByIdTest() {
        Dog expected = new Dog();
        expected.setName("testName");
        expected.setBreed("testBreed");
        expected.setDescription("testDesc");
        expected.setYearOfBirth(2017);

        Mockito.when(dogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));

        Dog actual = dogService.getById(1L);

        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getBreed()).isEqualTo(expected.getBreed());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
    }

    @Test
    public void getByIdExceptionTest() {
        Mockito.when(dogRepositoryMock.findById(1L)).thenThrow(DogNotFoundException.class);

        org.junit.jupiter.api.Assertions.assertThrows(DogNotFoundException.class, () -> dogService.getById(1L));
    }

    @Test
    public void createTest() {
        Dog expected = new Dog();
        expected.setName("testName");
        expected.setBreed("testBreed");
        expected.setDescription("testDesc");
        expected.setYearOfBirth(2017);

        Mockito.when(dogRepositoryMock.save(any(Dog.class))).thenReturn(expected);

        Dog actual = dogService.create(expected);

        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getBreed()).isEqualTo(expected.getBreed());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
    }

    @Test
    public void updateTest() {
        Dog expected = new Dog();
        expected.setName("testName");
        expected.setBreed("testBreed");
        expected.setDescription("testDesc");
        expected.setYearOfBirth(2017);
        expected.setId(1L);

        Mockito.when(dogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));
        Mockito.when(dogRepositoryMock.save(any(Dog.class))).thenReturn(expected);

        Dog actual = dogService.update(expected);

        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getBreed()).isEqualTo(expected.getBreed());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
    }

    @Test
    public void updateExceptionTest() {
        Dog expected = new Dog();

        org.junit.jupiter.api.Assertions.assertThrows(DogNotFoundException.class, () -> dogService.update(expected));
    }

    @Test
    public void getAllTest() {
        List<Dog> expected = new ArrayList<>();

        Dog testDog1 = new Dog();
        testDog1.setName("testName");
        testDog1.setBreed("testBreed");
        testDog1.setDescription("testDesc");
        testDog1.setYearOfBirth(2017);
        expected.add(testDog1);

        Dog testDog2 = new Dog();
        testDog2.setName("testName");
        testDog2.setBreed("testBreed");
        testDog2.setDescription("testDesc");
        testDog2.setYearOfBirth(2017);
        expected.add(testDog2);

        Dog testDog3 = new Dog();
        testDog3.setName("testName");
        testDog3.setBreed("testBreed");
        testDog3.setDescription("testDesc");
        testDog3.setYearOfBirth(2017);
        expected.add(testDog3);

        Mockito.when(dogRepositoryMock.findAll()).thenReturn(expected);

        Collection<Dog> actual = dogService.getAll();

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
