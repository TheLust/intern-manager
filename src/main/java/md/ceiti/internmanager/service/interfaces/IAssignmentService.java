package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IAssignmentService {
    Optional<Assignment> findById(Long id);
    Optional<Assignment> findByEmployeeAndProject(Employee employee, Project project);
    List<Assignment> findAll();
    Assignment save(Assignment assignment);
    Assignment update(Assignment oldAssignment, Assignment newAssignment);
    void delete(Long id);
}
