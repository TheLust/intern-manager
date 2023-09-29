package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    Optional<Department> findById(Long id);
    Optional<Department> findByName(String name);
    List<Department> findAll();
    Department save(Department department);
    Department update(Department oldDepartment, Department newDepartment);
    void delete(Long id);
}
