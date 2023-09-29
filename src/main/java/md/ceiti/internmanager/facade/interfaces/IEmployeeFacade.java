package md.ceiti.internmanager.facade.interfaces;

import jakarta.validation.Valid;
import md.ceiti.internmanager.dto.EmployeeDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IEmployeeFacade {
    EmployeeDto find(Long id,
                     String email,
                     String phoneNumber);
    List<EmployeeDto> findAll();
    EmployeeDto save(Long departmentId,
                     Long jobId,
                     EmployeeDto employeeDto,
                     BindingResult bindingResult);
    EmployeeDto update(Long id,
                       Long departmentId,
                       Long jobId,
                       EmployeeDto employeeDto,
                       BindingResult bindingResult);
    String delete(Long id);
}
