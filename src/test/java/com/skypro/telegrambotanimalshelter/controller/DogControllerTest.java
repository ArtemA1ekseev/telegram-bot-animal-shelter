package com.skypro.telegrambotanimalshelter.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.skypro.telegrambotanimalshelter.model.Dog;
import com.skypro.telegrambotanimalshelter.service.DogService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class for testing DogController
 * @see DogService
 * @author Artem Alekseev
 */
@WebMvcTest(DogController.class)
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    /**
     * Test for <b>getById()</b> method in DogController
     * <br>
     * Mockito: when <b>DogService::getById()</b> method called, returns <b>dog</b> object
     * @throws Exception
     */
    @Test
    void getById() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);

        when(dogService.getById(anyLong())).thenReturn(dog);

        mockMvc.perform(
                        get("/dog/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(dogService).getById(1L);
    }

    /**
     * Test for <b>save()</b> method in DogController
     * <br>
     * Mockito: when <b>DogService::create()</b> method called, returns <b>dog</b> object
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("dog");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "dog");

        when(dogService.create(dog)).thenReturn(dog);

        mockMvc.perform(
                        post("/dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(dogService).create(dog);
    }

    /**
     * Test for <b>update()</b> method in DogController
     * <br>
     * Mockito: when <b>DogService::getById()</b> method called, returns <b>dog</b> object
     * @throws Exception
     */
    @Test
    void update() throws Exception {
        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("dog new");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "dog new");

        when(dogService.update(dog)).thenReturn(dog);

        mockMvc.perform(
                        put("/dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(dogService).update(dog);
    }

    /**
     * Test for <b>remove()</b> method in DogController
     * @throws Exception
     */
    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/dog/{id}", 1))
                .andExpect(status().isOk());
        verify(dogService).removeById(1L);
    }

    /**
     * Test for <b>getAll()</b> method in DogController
     * <br>
     * Mockito: when <b>DogService::getAll()</b> method called, returns collection with <b>new Dog</b> object
     * @throws Exception
     */
    @Test
    void getAll() throws Exception {
        when(dogService.getAll()).thenReturn(List.of(new Dog()));

        mockMvc.perform(
                        get("/dog/all"))
                .andExpect(status().isOk());
    }
}
