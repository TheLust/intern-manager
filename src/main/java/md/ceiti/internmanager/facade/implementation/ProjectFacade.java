package md.ceiti.internmanager.facade.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.ProjectDto;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IProjectFacade;
import md.ceiti.internmanager.mapper.ProjectMapper;
import md.ceiti.internmanager.service.implementation.ProjectService;
import md.ceiti.internmanager.util.ErrorUtils;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.ProjectValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectFacade implements IProjectFacade {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    private final ProjectValidator projectValidator;

    @Override
    public ProjectDto find(Long id, String name) {

        if ((id != null ? 1 : 0) + (name != null ? 1 : 0) != 1) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        return findByName(name);
    }

    private ProjectDto findById(Long id) {
        Optional<Project> project = projectService.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        return projectMapper.toProjectDto(
                project.get()
        );
    }

    private ProjectDto findByName(String name) {
        Optional<Project> project = projectService.findByName(name);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        return projectMapper.toProjectDto(
                project.get()
        );
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectService.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .toList();
    }

    @Override
    public ProjectDto save(@Valid ProjectDto projectDto,
                           BindingResult bindingResult) {
        Project project = projectMapper.toProject(projectDto);

        projectValidator.validate(project, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return projectMapper.toProjectDto(
                projectService.save(project)
        );
    }

    @Override
    public ProjectDto update(Long id,
                             @Valid ProjectDto projectDto,
                             BindingResult bindingResult) {
        Optional<Project> oldProject = projectService.findById(id);
        if (oldProject.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        Project newProject = projectMapper.toProject(projectDto);
        projectValidator.validate(newProject, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return projectMapper.toProjectDto(
                projectService.update(oldProject.get(), newProject)
        );
    }

    @Override
    public String delete(Long id) {
        projectService.delete(id);

        return "Deleted";
    }
}
