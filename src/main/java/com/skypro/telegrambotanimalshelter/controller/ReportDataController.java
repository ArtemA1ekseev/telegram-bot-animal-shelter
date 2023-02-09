package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.skypro.telegrambotanimalshelter.listener.TelegramBotUpdatesListener;
import com.skypro.telegrambotanimalshelter.model.ReportData;
import com.skypro.telegrambotanimalshelter.service.ReportDataService;

import java.util.Collection;

/**
 * Class of ReportDataController.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@RestController
@RequestMapping("photoReports")
public class ReportDataController {

    private final ReportDataService reportDataService;
    @Autowired
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    private final String fileType = "image/jpeg";

    public ReportDataController(ReportDataService reportDataService) {
        this.reportDataService = reportDataService;
    }

    @Operation(summary = "Просмотр отчетов по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все отчеты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReportData.class)
                            )
                    )
            },
            tags = "Reports"
    )
    @GetMapping("/{id}/report")
    public ReportData downloadReport(@Parameter (description = "report id") @PathVariable Long id) {
        return this.reportDataService.findById(id);
    }

    @Operation(summary = "Удаление отчетов по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаленный отчет",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReportData.class)
                            )
                    )
            },
            tags = "Reports"
    )
    @DeleteMapping("/{id}")
    public void remove(@Parameter (description = "report id") @PathVariable Long id) {
        this.reportDataService.remove(id);
    }

    @Operation(summary = "Просмотр всех отчетов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все отчеты, либо отчеты определенного пользователя по chat_id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReportData.class)
                            )
                    )
            },
            tags = "Reports"
    )
    @GetMapping("/getAll")
    public ResponseEntity<Collection<ReportData>> getAll() {
        return ResponseEntity.ok(this.reportDataService.getAll());
    }

    @Operation(summary = "Просмотр фото по id отчета",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody (
                    description = "Фото, найденное по id отчета"
            ),
            tags = "Report"
    )
    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@Parameter (description = "report id") @PathVariable Long id) {
        ReportData reportData = this.reportDataService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }

    @Operation(summary = "Отправить сообщение пользователю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение определенному пользователю." +
                                    "Например сообщение о том, что необходимо правильно заполнять форму отчета. Либо связаться с волонтерами по номеру",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReportData.class)
                            )
                    )
            },
            tags = "Reports"
    )
    @GetMapping("/message-to-person")
    public void sendMessageToPerson(@Parameter(description = "id чата с пользователем", example = "3984892310")
                                    @RequestParam Long chat_Id,
                                    @Parameter(description = "Ваше сообщение")
                                    @RequestParam String message) {
        this.telegramBotUpdatesListener.sendMessage(chat_Id, message);
    }
}
