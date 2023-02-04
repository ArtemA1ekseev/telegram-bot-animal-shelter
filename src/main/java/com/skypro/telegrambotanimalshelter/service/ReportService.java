package com.skypro.telegrambotanimalshelter.service;

import com.pengrad.telegrambot.model.File;
import com.skypro.telegrambotanimalshelter.model.Pet;
import com.skypro.telegrambotanimalshelter.model.Report;
import com.skypro.telegrambotanimalshelter.model.User;
import com.skypro.telegrambotanimalshelter.repository.PetRepository;
import com.skypro.telegrambotanimalshelter.repository.ReportRepository;
import com.skypro.telegrambotanimalshelter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.datical.liquibase.ext.init.InitProjectUtil.getExtension;
import static java.nio.file.StandardOpenOption.CREATE_NEW;



/** Class for daily reports from users
 * <br>
 * Properties: <b>userRepository</b>, <b>reportRepository</b>, <b>petRepository</b>
 * @version 0.0.1
 * @author ekaterina denieva
 */

@Service
public class ReportService {

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final PetRepository petRepository;

    /**
     * Path on the server where photos of animals from user reports are stored
     */
    @Value("${pet.photo.dir.path}")
    private String photoPetDir;


    public ReportService(UserRepository userRepository, ReportRepository reportRepository,
                         PetRepository petRepository, PetService petService) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.petRepository = petRepository;

    }

    /**
     * The method returns all reports for a specific animal
     *
     * @param petId Id animal
     * @return List of TrusteesReports
     */
    public List<Report> getAllPetReports(Long petId) {
        return reportRepository.findAllByPet(petId);
    }

    /**
     * Method for downloading a report
     */
    public void uploadReport(Long personId, Long petId, String description, String petDiet, byte[] pictureFile, File file,
                                 String path, Date ReportDate, MultipartFile files) throws IOException {
        Optional<Pet> pet = petRepository.findById(petId);
        Path filePath = Path.of(photoPetDir, pet + "." + getExtension(files.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = files.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);

            Report report = new Report();
            report.setReportDate(ReportDate);
            report.setDescription(description);
            report.setPetDiet(petDiet);
            report.setFilePath(path);
            report.setId(personId);
            report.setFileSize(file.fileSize());
            report.setData(pictureFile);
            reportRepository.save(report);
        }
    }

    /**
     * Method for save a report
     */
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    /**
     * Method for delete a report
     */
    public void remove(Long id) {
        reportRepository.deleteById(id);

    }
}
