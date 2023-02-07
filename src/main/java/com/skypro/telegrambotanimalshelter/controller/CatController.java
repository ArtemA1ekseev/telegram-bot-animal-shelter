package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import com.skypro.telegrambotanimalshelter.model.Cat;
import com.skypro.telegrambotanimalshelter.service.CatService;

import java.util.Collection;

/**
 * Class of CatController.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@RestController
@RequestMapping("cat")
public class CatController {

    private final CatService service;

    public CatController(CatService service) {
        this.service = service;
    }

    @Operation(summary = "Получение питомца по id")
    @GetMapping("/{id}")
    public Cat getById(@PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Создание питомца")
    @PostMapping()
    public Cat save(@RequestBody Cat cat) {
        return this.service.create(cat);
    }

    @Operation(summary = "Изменение данных у питомца")
    @PutMapping()
    public Cat update(@RequestBody Cat cat) {
        return this.service.update(cat);
    }

    @Operation(summary = "Удаление питомца по id")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        this.service.removeById(id);
    }

    @Operation(summary = "Просмотр всех питомцев")
    @GetMapping("/all")
    public Collection<Cat> getAll() {
        return this.service.getAll();
    }
}