package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.DepartmentDto;
import md.ceiti.internmanager.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    private final ModelMapper mapper;

    public Department toDepartment(DepartmentDto departmentDto) {
        return mapper.map(departmentDto, Department.class);
    }

    public DepartmentDto toDepartmentDto(Department department) {
        return mapper.map(department, DepartmentDto.class);
    }
}
