package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.EmployeeDto;
import md.ceiti.internmanager.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final ModelMapper mapper;

    public Employee toEmployee(EmployeeDto employeeDto) {
        return mapper.map(employeeDto, Employee.class);
    }

    public EmployeeDto toEmployeeDto(Employee employee) {
        return mapper.map(employee, EmployeeDto.class);
    }
}
