package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Получение питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Кот, найденный по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @GetMapping("/{id}")
    public Cat getById(@Parameter (description = "cat id") @PathVariable Long id) {
        return this.service.getById(id);
    }

    @Operation(summary = "Создание питомца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Созданный питомец",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            tags = "Cat"
    )
    @PostMapping()
    public Cat save(@RequestBody Cat cat) {
        return this.service.create(cat);
    }

    @Operation(summary = "Изменение данных у питомца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Питомец, с измененными данными",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            tags = "Cat"
    )
    @PutMapping()
    public Cat update(@RequestBody Cat cat) {
        return this.service.update(cat);
    }

    @Operation(summary = "Удаление питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаленный питомец",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter (description = "cat id") @PathVariable Long id) {
        this.service.removeById(id);
    }

    @Operation(summary = "Просмотр всех питомцев",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все питомцы",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    )
            },
            tags = "Cat"
    )
    @GetMapping("/all")
    public Collection<Cat> getAll() {
        return this.service.getAll();
    }
}