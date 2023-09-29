package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Optional<Project> findById(Long id);
    Optional<Project> findByName(String name);
    List<Project> findAll();
    Project save(Project project);
    Project update(Project oldProject, Project newProject);
    void delete(Long id);
}
