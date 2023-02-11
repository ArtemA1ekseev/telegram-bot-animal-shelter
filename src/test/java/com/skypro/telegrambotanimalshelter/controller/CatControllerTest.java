package com.skypro.telegrambotanimalshelter.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.skypro.telegrambotanimalshelter.model.Cat;
import com.skypro.telegrambotanimalshelter.service.CatService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class for testing CatController
 * @see CatService
 * @author Artem Alekseev
 */
@WebMvcTest(CatController.class)
class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    /**
     * Test for <b>getById()</b> method in CatController
     * <br>
     * Mockito: when <b>CatService::getById()</b> method called, returns <b>cat</b> object
     * @throws Exception
     */
    @Test
    void getById() throws Exception {
        Cat cat = new Cat();
        cat.setId(1L);

        when(catService.getById(anyLong())).thenReturn(cat);

        mockMvc.perform(
                        get("/cat/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(catService).getById(1L);
    }

    /**
     * Test for <b>save()</b> method in CatController
     * <br>
     * Mockito: when <b>CatService::create()</b> method called, returns <b>cat</b> object
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("cat");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "cat");

        when(catService.create(cat)).thenReturn(cat);

        mockMvc.perform(
                        post("/cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(catService).create(cat);
    }

    /**
     * Test for <b>update()</b> method in CatController
     * <br>
     * Mockito: when <b>CatService::update()</b> method called, returns <b>cat</b> object
     * @throws Exception
     */
    @Test
    void update() throws Exception {
        Cat cat = new Cat();
        cat.setId(1L);
        cat.setName("cat new");
        JSONObject userObject = new JSONObject();
        userObject.put("id", 1L);
        userObject.put("name", "cat new");

        when(catService.update(cat)).thenReturn(cat);

        mockMvc.perform(
                        put("/cat")
                                .content(userObject.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userObject.toString()));

        verify(catService).update(cat);
    }

    /**
     * Test for <b>remove()</b> method in CatController
     * @throws Exception
     */
    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/cat/{id}", 1))
                .andExpect(status().isOk());
        verify(catService).removeById(1L);
    }

    /**
     * Test for <b>getAll()</b> method in CatController
     * <br>
     * Mockito: when <b>CatService::getAll()</b> method called, returns collection with <b>new Cat</b> object
     * @throws Exception
     */
    @Test
    void getAll() throws Exception {
        when(catService.getAll()).thenReturn(List.of(new Cat()));

        mockMvc.perform(
                        get("/cat/all"))
                .andExpect(status().isOk());
    }
}