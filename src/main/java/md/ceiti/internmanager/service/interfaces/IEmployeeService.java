package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> findById(Long id);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByPhoneNumber(String phoneNumber);
    List<Employee> findAll();
    Employee save(Employee employee);
    Employee update(Employee oldEmployee, Employee newEmployee);
    void delete(Long id);
}
