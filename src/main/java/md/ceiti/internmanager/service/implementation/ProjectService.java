package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.ProjectDao;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.service.interfaces.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService  implements IProjectService {
    private final ProjectDao projectDao;


    @Override
    public Optional<Project> findById(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public Optional<Project> findByName(String name) {
        return projectDao.findByName(name);
    }

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public Project save(Project project) {
        return projectDao.save(project);
    }

    @Override
    public Project update(Project oldProject, Project newProject) {
        BeanUtils.copyProperties(newProject, oldProject, "id");
        return projectDao.save(oldProject);
    }

    @Override
    public void delete(Long id) {
        projectDao.deleteById(id);
    }
}
