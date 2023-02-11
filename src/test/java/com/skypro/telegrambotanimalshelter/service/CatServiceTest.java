package com.skypro.telegrambotanimalshelter.service;

import com.skypro.telegrambotanimalshelter.exceptions.CatNotFoundException;
import com.skypro.telegrambotanimalshelter.model.Cat;
import com.skypro.telegrambotanimalshelter.repository.CatRepository;
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

/**
 * Class for testing CatService
 * @see CatService
 * @see CatRepository
 * @author Bazarov Bulat
 */
@ExtendWith(MockitoExtension.class)
public class CatServiceTest {

    @Mock
    private CatRepository catRepositoryMock;

    @InjectMocks
    private CatService catService;

    /**
     * Test for method <b>getById()</b> in CatService
     * <br>
     * Mockito: when <b>CatRepository::findById()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void getByIdTest() {
        Cat expected = new Cat();
        expected.setName("testName");
        expected.setDescription("testDesc");
        expected.setBreed("testBreed");
        expected.setYearOfBirth(2021);

        Mockito.when(catRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));

        Cat actual = catService.getById(1L);

        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getBreed()).isEqualTo(expected.getBreed());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
    }

    /**
     * Test for throwing an exception in method <b>getById()</b> in CatService
     * <br>
     * Mockito: when <b>CatRepository::findById()</b> method called, throws <b>CatNotFoundException</b>
     */
    @Test
    public void getByIdExceptionTest() {
        Mockito.when(catRepositoryMock.findById(any(Long.class))).thenThrow(CatNotFoundException.class);

        org.junit.jupiter.api.Assertions.assertThrows(CatNotFoundException.class, () -> catService.getById(1L));
    }

    /**
     * Test for method <b>create()</b> in CatService
     * <br>
     * Mockito: when <b>CatRepository::save()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void createTest() {
        Cat expected = new Cat();
        expected.setName("testName");
        expected.setDescription("testDesc");
        expected.setBreed("testBreed");
        expected.setYearOfBirth(2021);

        Mockito.when(catRepositoryMock.save(any(Cat.class))).thenReturn(expected);

        Cat actual = catService.create(expected);

        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getBreed()).isEqualTo(expected.getBreed());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
    }

    /**
     * Test for method <b>update()</b> in CatService
     * <br>
     * Mockito: when <b>CatRepository::save()</b> method called, returns <b>expected</b> object
     * <br>
     * Mockito: when <b>CatRepository::findById()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void updateTest() {
        Cat expected = new Cat();
        expected.setName("testName");
        expected.setDescription("testDesc");
        expected.setBreed("testBreed");
        expected.setYearOfBirth(2021);
        expected.setId(1L);

        Mockito.when(catRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));
        Mockito.when(catRepositoryMock.save(any(Cat.class))).thenReturn(expected);

        Cat actual = catService.update(expected);

        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getBreed()).isEqualTo(expected.getBreed());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        Assertions.assertThat(actual.getYearOfBirth()).isEqualTo(expected.getYearOfBirth());
    }

    /**
     * Test for throwing an exception in method <b>update()</b> in CatService
     * <br>
     * Creating a <b>Cat</b> object with null id
     */
    @Test
    public void updateExceptionTest() {
        Cat expected = new Cat();

        org.junit.jupiter.api.Assertions.assertThrows(CatNotFoundException.class, () -> catService.update(expected));
    }

    /**
     * Test for method <b>getAll()</b> in CatService
     * <br>
     * Mockito: when <b>CatRepository::findAll()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void getAllTest() {
        List<Cat> expected = new ArrayList<>();

        Cat testCat1 = new Cat();
        testCat1.setName("testName1");
        testCat1.setDescription("testDesc1");
        testCat1.setBreed("testBreed1");
        testCat1.setYearOfBirth(2019);
        expected.add(testCat1);

        Cat testCat2 = new Cat();
        testCat2.setName("testName2");
        testCat2.setDescription("testDesc2");
        testCat2.setBreed("testBreed2");
        testCat2.setYearOfBirth(2018);
        expected.add(testCat2);

        Cat testCat3 = new Cat();
        testCat3.setName("testName3");
        testCat3.setDescription("testDesc3");
        testCat3.setBreed("testBreed3");
        testCat3.setYearOfBirth(2021);
        expected.add(testCat3);

        Mockito.when(catRepositoryMock.findAll()).thenReturn(expected);

        Collection<Cat> actual = catService.getAll();

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
