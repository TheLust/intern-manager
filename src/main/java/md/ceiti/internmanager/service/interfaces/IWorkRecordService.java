package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.WorkRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IWorkRecordService {
    Optional<WorkRecord> findById(Long id);
    Optional<WorkRecord> findByAssignmentAndDate(Assignment assignment, LocalDate date);
    List<WorkRecord> findAll();
    WorkRecord save(WorkRecord workRecord);
    WorkRecord update(WorkRecord oldWorkRecord, WorkRecord newWorkRecord);
    void delete(Long id);
}
