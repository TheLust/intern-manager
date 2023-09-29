package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        if (employeeService.findByEmail(employee.getEmail()).isPresent()) {
            errors.rejectValue("email", AlreadyExistsException.EMPLOYEE);
        }

        if (employeeService.findByPhoneNumber(employee.getPhoneNumber()).isPresent()) {
            errors.rejectValue("phoneNumber", AlreadyExistsException.EMPLOYEE);
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Employee employee = (Employee) target;

        Optional<Employee> emailEmployee = employeeService.findByEmail(employee.getEmail());
        if (emailEmployee.isPresent()) {
            if (!Objects.equals(emailEmployee.get().getId(), id)) {
                errors.rejectValue("email", AlreadyExistsException.EMPLOYEE);
            }
        }

        Optional<Employee> phoneNumberEmployee = employeeService.findByPhoneNumber(employee.getPhoneNumber());
        if (phoneNumberEmployee.isPresent()) {
            if (!Objects.equals(phoneNumberEmployee.get().getId(), id)) {
                errors.rejectValue("phoneNumber", AlreadyExistsException.EMPLOYEE);
            }
        }
    }
}
