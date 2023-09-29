package md.ceiti.internmanager.facade.interfaces;

import md.ceiti.internmanager.dto.ProjectDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IProjectFacade {
    ProjectDto find(Long id, String name);
    List<ProjectDto> findAll();
    ProjectDto save(ProjectDto projectDto,
                    BindingResult bindingResult);
    ProjectDto update(Long id,
                      ProjectDto projectDto,
                      BindingResult bindingResult);
    String delete(Long id);
}
