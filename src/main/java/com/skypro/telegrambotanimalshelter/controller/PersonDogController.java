package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Получение пользователя по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, найденный по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonDog.class)
                            )
                    )
            },
            tags = "PersonDog"
    )
    @GetMapping("/{id}")
    public PersonDog getById(@Parameter(description = "PersonDog id")@PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Создание пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Созданный пользователь",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDog.class)
                    )
            ),
            tags = "PersonDog"
    )
    @PostMapping
    public PersonDog save(@RequestBody PersonDog personDog) {
        return this.service.create(personDog);
    }

    @Operation(summary = "Изменение данных пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Пользователь, с измененными данными",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDog.class)
                    )
            ),
            tags = "PersonDog"
    )
    @PutMapping
    public PersonDog update(@RequestBody PersonDog personDog) {
        return this.service.update(personDog);
    }

    @Operation(summary = "Удаление пользователей по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь, удаленный по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonDog.class)
                            )
                    )
            },
            tags = "PersonDog"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter(description = "PersonDog id") @PathVariable Long id) {
        this.service.removeById(id);
    }

    @Operation(summary = "Просмотр всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все пользователи, либо определенные пользователи по chat_id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonDog.class)
                            )
                    )
            },
            tags = "PersonDog"
    )
    @GetMapping("/all")
    public Collection<PersonDog> getAll(@RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            return this.service.getByChatId(chatId);
        }
        return this.service.getAll();
    }
}
