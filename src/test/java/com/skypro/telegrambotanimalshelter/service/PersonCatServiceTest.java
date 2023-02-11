package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.exceptions.PersonCatNotFoundException;
import com.skypro.telegrambotanimalshelter.model.PersonCat;
import com.skypro.telegrambotanimalshelter.repository.PersonCatRepository;
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
 * @see PersonCatService
 * @see PersonCatRepository
 * @author Bazarov Bulat
 */
@ExtendWith(MockitoExtension.class)
public class PersonCatServiceTest {

    @Mock
    private PersonCatRepository personCatRepositoryMock;

    @InjectMocks
    private PersonCatService personCatService;

    /**
     * Test for method <b>getById()</b> in PersonCatService
     * <br>
     * Mockito: when <b>PersonCatRepository::findById()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void getByIdTest() {
        PersonCat expected = new PersonCat(1L, "testName", 2000, "testPhone", "testMail", "testAddress", 1L);

        Mockito.when(personCatRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));

        PersonCat actual = personCatService.getById(1L);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getMail()).isEqualTo(expected.getMail());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
    }

    /**
     * Test for throwing an exception in method <b>getById()</b> in PersonCatService
     * <br>
     * Mockito: when <b>PersonCatRepository::findById()</b> method called, throws <b>PersonCatNotFoundException</b>
     */
    @Test
    public void getByIdExceptionTest() {
        Mockito.when(personCatRepositoryMock.findById(any(Long.class))).thenThrow(PersonCatNotFoundException.class);

        org.junit.jupiter.api.Assertions.assertThrows(PersonCatNotFoundException.class, () -> personCatService.getById(1L));
    }

    /**
     * Test for method <b>create()</b> in PersonCatService
     * <br>
     * Mockito: when <b>PersonCatRepository::save()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void createTest() {
        PersonCat expected = new PersonCat(1L, "testName", 2000, "testPhone", "testMail", "testAddress", 1L);

        Mockito.when(personCatRepositoryMock.save(any(PersonCat.class))).thenReturn(expected);

        PersonCat actual = personCatService.create(expected);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getMail()).isEqualTo(expected.getMail());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
    }

    /**
     * Test for method <b>update()</b> in PersonCatService
     * <br>
     * Mockito: when <b>PersonCatRepository::save()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void updateTest() {
        PersonCat expected = new PersonCat(1L, "testName", 2000, "testPhone", "testMail", "testAddress", 1L);

        Mockito.when(personCatRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));
        Mockito.when(personCatRepositoryMock.save(any(PersonCat.class))).thenReturn(expected);

        PersonCat actual = personCatService.update(expected);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
        Assertions.assertThat(actual.getPhone()).isEqualTo(expected.getPhone());
        Assertions.assertThat(actual.getMail()).isEqualTo(expected.getMail());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
    }

    /**
     * Test for throwing an exception in method <b>update()</b> in PersonCatService
     * <br>
     * Creating a <b>PersonCat</b> object with null id
     */
    @Test
    public void updateExceptionTest() {
        PersonCat expected = new PersonCat();

        org.junit.jupiter.api.Assertions.assertThrows(PersonCatNotFoundException.class, () -> personCatService.update(expected));
    }

    /**
     * Test for method <b>getAll()</b> in PersonCatService
     * <br>
     * Mockito: when <b>PersonCatRepository::findAll()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void getAllTest() {
        List<PersonCat> expected = new ArrayList<>();

        PersonCat testPerson1 = new PersonCat(1L, "testName1", 1995, "testPhone1", "testMail1", "testAddress1", 1L);
        expected.add(testPerson1);

        PersonCat testPerson2 = new PersonCat(2L, "testName1", 1997, "testPhone2", "testMail2", "testAddress2", 2L);
        expected.add(testPerson2);

        PersonCat testPerson3 = new PersonCat(3L, "testName3", 2005, "testPhone3", "testMail3", "testAddress3", 3L);
        expected.add(testPerson3);

        Mockito.when(personCatRepositoryMock.findAll()).thenReturn(expected);

        Collection<PersonCat> actual = personCatService.getAll();

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /**
     * Test for method <b>getByChatId()</b> in PersonCatService
     * <br>
     * Mockito: when <b>PersonCatRepository::findByChatId()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void getByChatIdTest() {
        Set<PersonCat> expected = new HashSet<>();

        PersonCat testPerson1 = new PersonCat(1L, "testName1", 1995, "testPhone1", "testMail1", "testAddress1", 1L);
        expected.add(testPerson1);

        PersonCat testPerson2 = new PersonCat(2L, "testName1", 1997, "testPhone2", "testMail2", "testAddress2", 2L);
        expected.add(testPerson2);

        PersonCat testPerson3 = new PersonCat(3L, "testName3", 2005, "testPhone3", "testMail3", "testAddress3", 3L);
        expected.add(testPerson3);

        Mockito.when(personCatRepositoryMock.findByChatId(any(Long.class))).thenReturn(expected);

        Collection<PersonCat> actual = personCatService.getByChatId(1L);

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
