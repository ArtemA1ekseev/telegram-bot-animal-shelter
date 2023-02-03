package com.skypro.telegrambotanimalshelter.repository;

import com.skypro.telegrambotanimalshelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByPet(Long petId);
}
