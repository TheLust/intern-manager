package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.EmployeeDao;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.service.interfaces.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Optional<Employee> findByPhoneNumber(String phoneNumber) {
        return employeeDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee update(Employee oldEmployee, Employee newEmployee) {
        BeanUtils.copyProperties(newEmployee, oldEmployee, "id");
        return employeeDao.save(oldEmployee);
    }

    @Override
    public void delete(Long id) {
        employeeDao.deleteById(id);
    }
}
