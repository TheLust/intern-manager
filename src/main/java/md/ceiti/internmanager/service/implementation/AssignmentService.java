package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.AssignmentDao;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.service.interfaces.IAssignmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService {

    private final AssignmentDao assignmentDao;

    @Override
    public Optional<Assignment> findById(Long id) {
        return assignmentDao.findById(id);
    }

    @Override
    public Optional<Assignment> findByEmployeeAndProject(Employee employee, Project project) {
        return assignmentDao.findByEmployeeAndProject(employee, project);
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentDao.findAll();
    }

    @Override
    public Assignment save(Assignment assignment) {
        return assignmentDao.save(assignment);
    }

    @Override
    public Assignment update(Assignment oldAssignment, Assignment newAssignment) {
        BeanUtils.copyProperties(newAssignment, oldAssignment, "id");
        return assignmentDao.save(oldAssignment);
    }

    @Override
    public void delete(Long id) {
        assignmentDao.deleteById(id);
    }
}
