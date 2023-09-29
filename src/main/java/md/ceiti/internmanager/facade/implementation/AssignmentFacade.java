package md.ceiti.internmanager.facade.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.AssignmentDto;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IAssignmentFacade;
import md.ceiti.internmanager.mapper.AssignmentMapper;
import md.ceiti.internmanager.service.implementation.AssignmentService;
import md.ceiti.internmanager.service.implementation.EmployeeService;
import md.ceiti.internmanager.service.implementation.ProjectService;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.AssignmentValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssignmentFacade implements IAssignmentFacade {

    private final AssignmentService assignmentService;

    private final EmployeeService employeeService;

    private final ProjectService projectService;

    private final AssignmentMapper assignmentMapper;

    private final AssignmentValidator assignmentValidator;

    @Override
    public AssignmentDto find(Long id,
                              Long employeeId,
                              Long projectId) {

        if ((id != null ? 1 : 0) + ((employeeId != null ? 1 : 0) * (projectId != null ? 1 : 0)) != 1) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        return findByEmployeeAndProject(employeeId, projectId);
    }

    private AssignmentDto findById(Long id) {
        Optional<Assignment> assignment = assignmentService.findById(id);
        if (assignment.isEmpty()) {
            throw new NotFoundException(Assignment.class);
        }

        return assignmentMapper.toAssignmentDto(
                assignment.get()
        );
    }

    private AssignmentDto findByEmployeeAndProject(Long employeeId,
                                                   Long projectId) {

        Optional<Employee> employee = employeeService.findById(employeeId);
        if (employee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        Optional<Project> project = projectService.findById(projectId);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        Optional<Assignment> assignment = assignmentService.findByEmployeeAndProject(employee.get(), project.get());
        if (assignment.isEmpty()) {
            throw new NotFoundException(Assignment.class);
        }

        return assignmentMapper.toAssignmentDto(
                assignment.get()
        );
    }

    @Override
    public List<AssignmentDto> findAll() {
        return assignmentService.findAll()
                .stream()
                .map(assignmentMapper::toAssignmentDto)
                .toList();
    }

    @Override
    public AssignmentDto save(Long employeeId,
                              Long projectId) {
        Optional<Employee> employee = employeeService.findById(employeeId);
        if (employee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        Optional<Project> project = projectService.findById(projectId);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        Assignment assignment = new Assignment();
        assignment.setEmployee(employee.get());
        assignment.setProject(project.get());

        assignmentValidator.validate(assignment);

        return assignmentMapper.toAssignmentDto(
                assignmentService.save(assignment)
        );
    }

    @Override
    public AssignmentDto update(Long id, Long employeeId, Long projectId) {
        Optional<Assignment> oldAssignment = assignmentService.findById(id);
        if (oldAssignment.isEmpty()) {
            throw new NotFoundException(Assignment.class);
        }

        Optional<Employee> employee = employeeService.findById(employeeId);
        if (employee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        Optional<Project> project = projectService.findById(projectId);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        Assignment newAssignment = new Assignment();
        newAssignment.setEmployee(employee.get());
        newAssignment.setProject(project.get());

        assignmentValidator.validate(newAssignment);

        return assignmentMapper.toAssignmentDto(
                assignmentService.update(oldAssignment.get(), newAssignment)
        );
    }

    @Override
    public String delete(Long id) {
        assignmentService.delete(id);

        return "Deleted";
    }
}
