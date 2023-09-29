package md.ceiti.internmanager.facade.interfaces;

import md.ceiti.internmanager.dto.AssignmentDto;

import java.util.List;

public interface IAssignmentFacade {
    AssignmentDto find(Long id,
                       Long employeeId,
                       Long projectId);
    List<AssignmentDto> findAll();
    AssignmentDto save(Long employeeId,
                       Long projectId);
    AssignmentDto update(Long id,
                         Long employeeId,
                         Long projectId);
    String delete(Long id);
}
