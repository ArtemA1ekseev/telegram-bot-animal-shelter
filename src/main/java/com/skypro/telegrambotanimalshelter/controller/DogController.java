package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.skypro.telegrambotanimalshelter.model.Dog;
import com.skypro.telegrambotanimalshelter.service.DogService;

import java.util.Collection;

/**
 * Class of DogController.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@RestController
@RequestMapping("dog")
public class DogController {

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @Operation(summary = "Получение питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака, найденная по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @GetMapping("/{id}")
    public Dog getById(@Parameter(description = "dog id") @PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Создание собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Созданная собака",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Dog.class)
                    )
            ),
            tags = "Dog"
    )
    @PostMapping()
    public Dog save(@RequestBody Dog dog) {
        return this.service.create(dog);
    }

    @Operation(summary = "Изменение данных у собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Собака с измененными данными",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Dog.class)
                    )
            ),
            tags = "Dog"
    )
    @PutMapping()
    public Dog update(@RequestBody Dog dog) {
        return this.service.update(dog);
    }

    @Operation(summary = "Удаление собаки по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаленная собака",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter (description = "dog id")@PathVariable Long id) {
        this.service.removeById(id);
    }

    @Operation(summary = "Просмотр всех собак",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все собаки",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            },
            tags = "Dog"
    )
    @GetMapping("/all")
    public Collection<Dog> getAll() {
        return this.service.getAll();
    }
}
