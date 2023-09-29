package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.WorkRecordDao;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.WorkRecord;
import md.ceiti.internmanager.service.interfaces.IWorkRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkRecordService implements IWorkRecordService {

    private final WorkRecordDao workRecordDao;

    @Override
    public Optional<WorkRecord> findById(Long id) {
        return workRecordDao.findById(id);
    }

    @Override
    public Optional<WorkRecord> findByAssignmentAndDate(Assignment assignment, LocalDate date) {
        return workRecordDao.findByAssignmentAndDate(assignment, date);
    }

    @Override
    public List<WorkRecord> findAll() {
        return workRecordDao.findAll();
    }

    @Override
    public WorkRecord save(WorkRecord workRecord) {
        return workRecordDao.save(workRecord);
    }

    @Override
    public WorkRecord update(WorkRecord oldWorkRecord, WorkRecord newWorkRecord) {
        BeanUtils.copyProperties(newWorkRecord, oldWorkRecord,
                "id", "assignment", "date");
        return workRecordDao.save(oldWorkRecord);
    }

    @Override
    public void delete(Long id) {
        workRecordDao.deleteById(id);
    }
}
