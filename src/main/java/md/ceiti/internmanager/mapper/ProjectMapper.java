package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.ProjectDto;
import md.ceiti.internmanager.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ModelMapper mapper;

    public Project toProject(ProjectDto projectDto) {
        return mapper.map(projectDto, Project.class);
    }

    public ProjectDto toProjectDto(Project project) {
        return mapper.map(project, ProjectDto.class);
    }
}
