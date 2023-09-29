package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.Wage;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.WageService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class WageValidator implements Validator {

    private final WageService wageService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Wage.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Wage wage = (Wage) target;

        if (wageService.findByProjectAndJob(wage.getProject(), wage.getJob()).isPresent()) {
            errors.rejectValue("project", AlreadyExistsException.WAGE);
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Wage wage = (Wage) target;
        Optional<Wage> foundWage = wageService.findByProjectAndJob(wage.getProject(), wage.getJob());

        if (foundWage.isPresent()) {
            if (!Objects.equals(foundWage.get().getId(), id)) {
                errors.rejectValue("project", AlreadyExistsException.WAGE);
            }
        }
    }
}
