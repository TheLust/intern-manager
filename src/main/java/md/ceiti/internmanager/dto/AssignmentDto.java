package md.ceiti.internmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentDto {

    private Long id;

    private ProjectDto project;

    private EmployeeDto employee;
}
