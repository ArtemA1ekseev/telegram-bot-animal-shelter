package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import com.skypro.telegrambotanimalshelter.model.PersonDog;
import com.skypro.telegrambotanimalshelter.service.PersonDogService;

import java.util.Collection;

/**
 * Class of PersonDogController.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@RestController
@RequestMapping("person-dog")
public class PersonDogController {

    private final PersonDogService service;

    public PersonDogController(PersonDogService service) {
        this.service = service;
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public PersonDog getById(@PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping
    public PersonDog save(@RequestBody PersonDog personDog) {
        return this.service.create(personDog);
    }

    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public PersonDog update(@RequestBody PersonDog personDog) {
        return this.service.update(personDog);
    }

    @Operation(summary = "Удаление пользователей по id")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        this.service.removeById(id);
    }

    @Operation(summary = "Просмотр всех пользователей", description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("/all")
    public Collection<PersonDog> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return this.service.getByChatId(chatId);
        }
        return this.service.getAll();
    }
}
