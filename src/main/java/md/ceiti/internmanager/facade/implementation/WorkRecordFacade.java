package md.ceiti.internmanager.facade.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.WorkRecordDto;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.WorkRecord;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IWorkRecordFacade;
import md.ceiti.internmanager.mapper.WorkRecordMapper;
import md.ceiti.internmanager.service.implementation.AssignmentService;
import md.ceiti.internmanager.service.implementation.WorkRecordService;
import md.ceiti.internmanager.util.ErrorUtils;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.WorkRecordValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkRecordFacade implements IWorkRecordFacade {

    private final WorkRecordService workRecordService;

    private final AssignmentService assignmentService;

    private final WorkRecordMapper workRecordMapper;

    private final WorkRecordValidator workRecordValidator;

    @Override
    public WorkRecordDto find(Long id,
                              Long assignmentId,
                              LocalDate date) {
        if ((id != null ? 1 : 0) + ((assignmentId != null || date != null) ? 1 : 0) != 1) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        return findByAssignmentAndDate(assignmentId, date);
    }

    private WorkRecordDto findById(Long id) {
        Optional<WorkRecord> workRecord = workRecordService.findById(id);
        if (workRecord.isEmpty()) {
            throw new NotFoundException(WorkRecord.class);
        }

        return workRecordMapper.toWorkRecordDto(
                workRecord.get()
        );
    }

    private WorkRecordDto findByAssignmentAndDate(Long assignmentId,
                                                  LocalDate date) {
        Optional<Assignment> assignment = assignmentService.findById(assignmentId);
        if (assignment.isEmpty()) {
            throw new NotFoundException(Assignment.class);
        }

        Optional<WorkRecord> workRecord = workRecordService.findByAssignmentAndDate(assignment.get(), date);
        if (workRecord.isEmpty()) {
            throw new NotFoundException(WorkRecord.class);
        }

        return workRecordMapper.toWorkRecordDto(
                workRecord.get()
        );
    }

    @Override
    public List<WorkRecordDto> getAll() {
        return workRecordService.findAll()
                .stream()
                .map(workRecordMapper::toWorkRecordDto)
                .toList();
    }

    @Override
    public WorkRecordDto save(Long assignmentId,
                              WorkRecordDto workRecordDto,
                              BindingResult bindingResult) {
        Optional<Assignment> assignment = assignmentService.findById(assignmentId);
        if (assignment.isEmpty()) {
            throw new NotFoundException(Assignment.class);
        }

        WorkRecord workRecord = workRecordMapper.toWorkRecord(workRecordDto);
        workRecord.setAssignment(assignment.get());

        workRecordValidator.validate(workRecord, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return workRecordMapper.toWorkRecordDto(
                workRecordService.save(workRecord)
        );
    }

    @Override
    public WorkRecordDto update(Long id,
                                Long assignmentId,
                                WorkRecordDto workRecordDto,
                                BindingResult bindingResult) {
        Optional<WorkRecord> oldWorkRecord = workRecordService.findById(id);
        if (oldWorkRecord.isEmpty()) {
            throw new NotFoundException(WorkRecord.class);
        }

        WorkRecord newWorkRecord = workRecordMapper.toWorkRecord(workRecordDto);

        if (assignmentId != null) {
            Optional<Assignment> assignment = assignmentService.findById(assignmentId);
            if (assignment.isEmpty()) {
                throw new NotFoundException(Assignment.class);
            }
            newWorkRecord.setAssignment(assignment.get());
        } else {
            newWorkRecord.setAssignment(oldWorkRecord.get().getAssignment());
        }

        workRecordValidator.validate(id, newWorkRecord, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return workRecordMapper.toWorkRecordDto(
                workRecordService.update(oldWorkRecord.get(), newWorkRecord)
        );
    }

    @Override
    public String delete(Long id) {
        workRecordService.delete(id);

        return "Deleted";
    }
}
