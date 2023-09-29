package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.Department;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.DepartmentService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class DepartmentValidator implements Validator {

    private final DepartmentService departmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;

        if (departmentService.findByName(department.getName()).isPresent()) {
            errors.rejectValue("name", AlreadyExistsException.DEPARTMENT);
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Department department = (Department) target;
        Optional<Department> foundDepartment = departmentService.findByName(department.getName());

        if (foundDepartment.isPresent()) {
            if (!Objects.equals(foundDepartment.get().getId(), id)) {
                errors.rejectValue("name", AlreadyExistsException.DEPARTMENT);
            }
        }
    }
}
