package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class ProjectValidator implements Validator {

    private final ProjectService projectService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;

        if (projectService.findByName(project.getName()).isPresent()) {
            errors.rejectValue("name", AlreadyExistsException.PROJECT);
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Project project = (Project) target;
        Optional<Project> foundProject = projectService.findByName(project.getName());

        if (foundProject.isPresent()) {
            if (!Objects.equals(foundProject.get().getId(), id)) {
                errors.rejectValue("name", AlreadyExistsException.PROJECT);
            }
        }
    }
}
