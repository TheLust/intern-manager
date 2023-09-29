package md.ceiti.internmanager.facade.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.EmployeeDto;
import md.ceiti.internmanager.entity.Department;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IEmployeeFacade;
import md.ceiti.internmanager.mapper.EmployeeMapper;
import md.ceiti.internmanager.service.implementation.DepartmentService;
import md.ceiti.internmanager.service.implementation.EmployeeService;
import md.ceiti.internmanager.service.implementation.JobService;
import md.ceiti.internmanager.util.ErrorUtils;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.EmployeeValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeFacade implements IEmployeeFacade {

    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    private final JobService jobService;

    private final EmployeeMapper employeeMapper;

    private final EmployeeValidator employeeValidator;

    @Override
    public EmployeeDto find(Long id,
                            String email,
                            String phoneNumber) {

        if ((id != null ? 1 : 0) + (email != null ? 1 : 0) + (phoneNumber != null ? 1 : 0) != 1) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        if (email != null) {
            return findByEmail(email);
        }

        return findByPhoneNumber(phoneNumber);
    }

    private EmployeeDto findById(Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        return employeeMapper.toEmployeeDto(
                employee.get()
        );
    }

    private EmployeeDto findByEmail(String email) {
        Optional<Employee> employee = employeeService.findByEmail(email);
        if (employee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        return employeeMapper.toEmployeeDto(
                employee.get()
        );
    }

    private EmployeeDto findByPhoneNumber(String phoneNumber) {
        Optional<Employee> employee = employeeService.findByPhoneNumber(phoneNumber);
        if (employee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        return employeeMapper.toEmployeeDto(
                employee.get()
        );
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeService.findAll()
                .stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto save(Long departmentId,
                            Long jobId,
                            @Valid EmployeeDto employeeDto,
                            BindingResult bindingResult) {

        Optional<Department> department = departmentService.findById(departmentId);
        if (department.isEmpty()) {
            throw new NotFoundException(Department.class);
        }

        Optional<Job> job = jobService.findById(jobId);
        if (job.isEmpty()) {
            throw new NotFoundException(Job.class);
        }

        Employee employee = employeeMapper.toEmployee(employeeDto);
        employee.setDepartment(department.get());
        employee.setJob(job.get());

        employeeValidator.validate(employee, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return employeeMapper.toEmployeeDto(
                employeeService.save(employee)
        );
    }

    @Override
    public EmployeeDto update(Long id,
                              Long departmentId,
                              Long jobId,
                              @Valid EmployeeDto employeeDto,
                              BindingResult bindingResult) {
        Optional<Employee> oldEmployee = employeeService.findById(id);
        if (oldEmployee.isEmpty()) {
            throw new NotFoundException(Employee.class);
        }

        Employee newEmployee = employeeMapper.toEmployee(employeeDto);
        if (departmentId != null) {
            Optional<Department> department = departmentService.findById(departmentId);
            if (department.isEmpty()) {
                throw new NotFoundException(Department.class);
            }

            newEmployee.setDepartment(department.get());
        } else {
            newEmployee.setDepartment(oldEmployee.get().getDepartment());
        }

        if (jobId != null) {
            Optional<Job> job = jobService.findById(jobId);
            if (job.isEmpty()) {
                throw new NotFoundException(Job.class);
            }

            newEmployee.setJob(job.get());
        } else {
            newEmployee.setJob(oldEmployee.get().getJob());
        }

        employeeValidator.validate(id, newEmployee, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return employeeMapper.toEmployeeDto(
                employeeService.update(oldEmployee.get(), newEmployee)
        );
    }

    @Override
    public String delete(Long id) {
        employeeService.delete(id);

        return "Deleted";
    }
}
