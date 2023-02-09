package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.skypro.telegrambotanimalshelter.model.PersonCat;
import com.skypro.telegrambotanimalshelter.service.PersonCatService;

import java.util.Collection;

/**
 * Class of PersonCatController.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@RestController
@RequestMapping("person-cat")
public class PersonCatController {

    private final PersonCatService service;

    public PersonCatController(PersonCatService service) {
        this.service = service;
    }

    @Operation(summary = "Получение пользователя по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, найденный по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @GetMapping("/{id}")
    public PersonCat getById(@Parameter(description = "PersonCat id") @PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Создание пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Созданный пользователь",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonCat.class)
                    )
            ),
            tags = "PersonCat"
    )
    @PostMapping()
    public PersonCat save(@RequestBody PersonCat personCat) {
        return this.service.create(personCat);
    }

    @Operation(summary = "Изменение данных пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Пользователь, с измененными данными",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonCat.class)
                    )
            ),
            tags = "PersonCat"
    )
    @PutMapping
    public PersonCat update(@RequestBody PersonCat personCat) {
        return this.service.update(personCat);
    }

    @Operation(summary = "Удаление пользователей по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, удаленный по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "PersonCat id")@PathVariable Long id) {
        this.service.removeById(id);
    }

    @Operation(summary = "Просмотр всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все пользователи, либо определенные пользователи по chat_id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonCat.class)
                            )
                    )
            },
            tags = "PersonCat"
    )
    @GetMapping("/all")
    public Collection<PersonCat> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return this.service.getByChatId(chatId);
        }
        return this.service.getAll();
    }
}