package md.ceiti.internmanager.dao;

import jakarta.validation.constraints.NotNull;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WorkRecordDao extends JpaRepository<WorkRecord, Long> {
    Optional<WorkRecord> findByAssignmentAndDate(@NotNull Assignment assignment, @NotNull LocalDate date);
}
