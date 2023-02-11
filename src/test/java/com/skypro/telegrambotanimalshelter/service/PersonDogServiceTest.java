package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.exceptions.PersonDogNotFoundException;
import com.skypro.telegrambotanimalshelter.model.PersonDog;
import com.skypro.telegrambotanimalshelter.repository.PersonDogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

/**
 * Class for testing PersonCatService
 * @see PersonDogService
 * @see PersonDogRepository
 * @author Bazarov Bulat
 */
@ExtendWith(MockitoExtension.class)
public class PersonDogServiceTest {
    @Mock
    private PersonDogRepository personDogRepositoryMock;

    @InjectMocks
    private PersonDogService personDogService;

    /**
     * Test for method <b>getById()</b> in PersonDogService
     * <br>
     * Mockito: when <b>PersonDogRepository::findById()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void getByIdTest() {
        PersonDog expected = new PersonDog(1L, "testName", 2000, "testPhone", "testMail", "testAddress", 1L);

        Mockito.when(personDogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));

        PersonDog actual = personDogService.getById(1L);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getMail()).isEqualTo(expected.getMail());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
    }

    /**
     * Test for throwing an exception in method <b>getById()</b> in PersonDogService
     * <br>
     * Mockito: when <b>PersonDogRepository::findById()</b> method called, throws <b>PersonDogNotFoundException</b>
     */
    @Test
    public void getByIdExceptionTest() {
        Mockito.when(personDogRepositoryMock.findById(any(Long.class))).thenThrow(PersonDogNotFoundException.class);

        org.junit.jupiter.api.Assertions.assertThrows(PersonDogNotFoundException.class, () -> personDogService.getById(1L));
    }

    /**
     * Test for method <b>create()</b> in PersonDogService
     * <br>
     * Mockito: when <b>PersonDogRepository::save()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void createTest() {
        PersonDog expected = new PersonDog(1L, "testName", 2000, "testPhone", "testMail", "testAddress", 1L);

        Mockito.when(personDogRepositoryMock.save(any(PersonDog.class))).thenReturn(expected);

        PersonDog actual = personDogService.create(expected);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getMail()).isEqualTo(expected.getMail());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
    }

    /**
     * Test for method <b>update()</b> in PersonDogService
     * <br>
     * Mockito: when <b>PersonDogRepository::save()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void updateTest() {
        PersonDog expected = new PersonDog(1L, "testName", 2000, "testPhone", "testMail", "testAddress", 1L);

        Mockito.when(personDogRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));
        Mockito.when(personDogRepositoryMock.save(any(PersonDog.class))).thenReturn(expected);

        PersonDog actual = personDogService.update(expected);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getMail()).isEqualTo(expected.getMail());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
    }

    /**
     * Test for throwing an exception in method <b>update()</b> in PersonDogService
     * <br>
     * Creating a <b>PersonDog</b> object with null id
     */
    @Test
    public void updateExceptionTest() {
        PersonDog expected = new PersonDog();

        org.junit.jupiter.api.Assertions.assertThrows(PersonDogNotFoundException.class, () -> personDogService.update(expected));
    }

    /**
     * Test for method <b>getAll()</b> in PersonDogService
     * <br>
     * Mockito: when <b>PersonDogRepository::findAll()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void getAllTest() {
        List<PersonDog> expected = new ArrayList<>();

        PersonDog testPerson1 = new PersonDog(1L, "testName1", 1995, "testPhone1", "testMail1", "testAddress1", 1L);
        expected.add(testPerson1);

        PersonDog testPerson2 = new PersonDog(2L, "testName1", 1997, "testPhone2", "testMail2", "testAddress2", 2L);
        expected.add(testPerson2);

        PersonDog testPerson3 = new PersonDog(3L, "testName3", 2005, "testPhone3", "testMail3", "testAddress3", 3L);
        expected.add(testPerson3);

        Mockito.when(personDogRepositoryMock.findAll()).thenReturn(expected);

        Collection<PersonDog> actual = personDogService.getAll();

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /**
     * Test for method <b>getByChatId()</b> in PersonDogService
     * <br>
     * Mockito: when <b>PersonDogRepository::getByChatId()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void getByChatIdTest() {
        Set<PersonDog> expected = new HashSet<>();

        PersonDog testPerson1 = new PersonDog(1L, "testName1", 1995, "testPhone1", "testMail1", "testAddress1", 1L);
        expected.add(testPerson1);

        PersonDog testPerson2 = new PersonDog(2L, "testName1", 1997, "testPhone2", "testMail2", "testAddress2", 2L);
        expected.add(testPerson2);

        PersonDog testPerson3 = new PersonDog(3L, "testName3", 2005, "testPhone3", "testMail3", "testAddress3", 3L);
        expected.add(testPerson3);

        Mockito.when(personDogRepositoryMock.findByChatId(any(Long.class))).thenReturn(expected);

        Collection<PersonDog> actual = personDogService.getByChatId(1L);

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
