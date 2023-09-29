package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.AssignmentService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class AssignmentValidator implements Validator {

    private final AssignmentService assignmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Assignment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assignment assignment = (Assignment) target;

        if (assignmentService.findByEmployeeAndProject(assignment.getEmployee(), assignment.getProject()).isPresent()) {
            errors.rejectValue("employee", AlreadyExistsException.ASSIGNMENT);
        }
    }

    public void validate(Object target) {
        Assignment assignment = (Assignment) target;

        if (assignmentService.findByEmployeeAndProject(assignment.getEmployee(), assignment.getProject()).isPresent()) {
            throw new AlreadyExistsException(Assignment.class);
        }
    }
}
