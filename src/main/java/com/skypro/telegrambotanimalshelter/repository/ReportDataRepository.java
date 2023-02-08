package com.skypro.telegrambotanimalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.skypro.telegrambotanimalshelter.model.ReportData;
import java.util.Set;

/**
 * Interface ReportDataRepository.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, Long> {

    Set<ReportData> findListByChatId(Long id);

    ReportData findByChatId(Long id);

}
