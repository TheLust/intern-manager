package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.DepartmentDao;
import md.ceiti.internmanager.entity.Department;
import md.ceiti.internmanager.service.interfaces.IDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentDao departmentDao;

    @Override
    public Optional<Department> findById(Long id) {
        return departmentDao.findById(id);
    }

    @Override
    public Optional<Department> findByName(String name) {
        return departmentDao.findByName(name);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Department save(Department department) {
        return departmentDao.save(department);
    }

    @Override
    public Department update(Department oldDepartment, Department newDepartment) {
        BeanUtils.copyProperties(newDepartment, oldDepartment, "id");
        return departmentDao.save(oldDepartment);
    }

    @Override
    public void delete(Long id) {
        departmentDao.deleteById(id);
    }
}
