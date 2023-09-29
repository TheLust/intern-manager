package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.AssignmentDto;
import md.ceiti.internmanager.entity.Assignment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentMapper {

    private final ModelMapper mapper;

    public Assignment toAssignment(AssignmentDto assignmentDto) {
        return mapper.map(assignmentDto, Assignment.class);
    }

    public AssignmentDto toAssignmentDto(Assignment assignment) {
        return mapper.map(assignment, AssignmentDto.class);
    }
}
