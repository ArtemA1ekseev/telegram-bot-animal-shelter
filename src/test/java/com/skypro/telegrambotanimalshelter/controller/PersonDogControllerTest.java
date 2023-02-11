package com.skypro.telegrambotanimalshelter.controller;

import com.skypro.telegrambotanimalshelter.service.PersonCatService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.skypro.telegrambotanimalshelter.model.PersonDog;
import com.skypro.telegrambotanimalshelter.service.PersonDogService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class for testing PersonDogController
 * @see PersonDogService
 * @author Artem Alekseev
 */
@WebMvcTest(PersonDogController.class)
class PersonDogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDogService personDogService;

    /**
     * Test for <b>getById()</b> method in PersonDogController
     * <br>
     * Mockito: when <b>PersonDogService::getById()</b> method called, returns <b>personDog</b> object
     * @throws Exception
     */
    @Test
    void getById() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);

        when(personDogService.getById(anyLong())).thenReturn(personDog);

        mockMvc.perform(
                        get("/person-dog/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(personDogService).getById(1L);
    }

    /**
     * Test for <b>save()</b> method in PersonDogController
     * <br>
     * Mockito: when <b>PersonDogService::create()</b> method called, returns <b>personDog</b> object
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);
        personDog.setName("person");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "person");

        when(personDogService.create(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        post("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(personDogService).create(personDog);
    }

    /**
     * <br>
     * Test for <b>update()</b> method in PersonDogController
     * <br>
     * Mockito: when <b>PersonDogService::update()</b> method called, returns <b>personDog</b> object
     * @throws Exception
     */
    @Test
    void update() throws Exception {
        PersonDog personDog = new PersonDog();
        personDog.setId(1L);
        personDog.setName("person");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "person");

        when(personDogService.update(personDog)).thenReturn(personDog);

        mockMvc.perform(
                        put("/person-dog")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(personDogService).update(personDog);
    }

    /**
     * Test for <b>remove()</b> method in PersonDogController
     * @throws Exception
     */
    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/person-dog/{id}", 1))
                .andExpect(status().isOk());
        verify(personDogService).removeById(1L);
    }

    /**
     * Test for <b>getAll()</b> method in PersonDogController
     * <br>
     * Mockito: when <b>PersonDogService::getAll()</b> method called, returns collection with <b>new PersonDog</b> object
     * @throws Exception
     */
    @Test
    void getAll() throws Exception {
        when(personDogService.getAll()).thenReturn(List.of(new PersonDog()));

        mockMvc.perform(
                        get("/person-dog/all"))
                .andExpect(status().isOk());
    }
}
