package com.skypro.telegrambotanimalshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Просмотр отчетов по id")
    @GetMapping("/{id}/report")
    public ReportData downloadReport(@PathVariable Long id) {
        return this.reportDataService.findById(id);
    }

    @Operation(summary = "Удаление отчетов по id")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        this.reportDataService.remove(id);
    }

    @Operation(summary = "Просмотр всех отчетов", description = "Просмотр всех отчетов, либо всех отчетов определенного пользователя по chat_id")
    @GetMapping("/getAll")
    public ResponseEntity<Collection<ReportData>> getAll() {
        return ResponseEntity.ok(this.reportDataService.getAll());
    }

    @Operation(summary = "Просмотр фото по айди отчета")
    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@PathVariable Long id) {
        ReportData reportData = this.reportDataService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }

    @Operation(summary = "Отправить сообщение пользователю", description = "Тут можно написать любое сообщение определенному пользователю." +
            "Например сообщение о том, чтобы правильно заполнял форму отчета. Либо связался с волонтерами по номеру")
    @GetMapping("/message-to-person")
    public void sendMessageToPerson(@Parameter(description = "id чата с пользователем", example = "3984892310")
                                    @RequestParam Long chat_Id,
                                    @Parameter(description = "Ваше сообщение")
                                    @RequestParam String message) {
        this.telegramBotUpdatesListener.sendMessage(chat_Id, message);
    }
}
