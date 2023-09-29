package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.WorkRecord;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.WorkRecordService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class WorkRecordValidator implements Validator {

    private final WorkRecordService workRecordService;

    @Override
    public boolean supports(Class<?> clazz) {
        return WorkRecord.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WorkRecord workRecord = (WorkRecord) target;

        if (workRecordService.findByAssignmentAndDate(workRecord.getAssignment(), workRecord.getDate()).isPresent()) {
            errors.rejectValue("assignment", AlreadyExistsException.WORK_RECORD);
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        WorkRecord workRecord = (WorkRecord) target;
        Optional<WorkRecord> foundWorkRecord = workRecordService.findByAssignmentAndDate(workRecord.getAssignment(), workRecord.getDate());

        if (foundWorkRecord.isPresent()) {
            if (!Objects.equals(foundWorkRecord.get().getId(), id)) {
                errors.rejectValue("assignment", AlreadyExistsException.WORK_RECORD);
            }
        }
    }
}
