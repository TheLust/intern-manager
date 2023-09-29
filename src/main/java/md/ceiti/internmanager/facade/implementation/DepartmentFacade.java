package md.ceiti.internmanager.facade.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.DepartmentDto;
import md.ceiti.internmanager.entity.Department;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IDepartmentFacade;
import md.ceiti.internmanager.mapper.DepartmentMapper;
import md.ceiti.internmanager.service.implementation.DepartmentService;
import md.ceiti.internmanager.util.ErrorUtils;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.DepartmentValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentFacade implements IDepartmentFacade {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    private final DepartmentValidator departmentValidator;

    @Override
    public DepartmentDto find(Long id, String name) {
        if (id == null && name == null) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null && name != null) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        return findByName(name);
    }

    private DepartmentDto findById(Long id) {

        Optional<Department> department = departmentService.findById(id);
        if (department.isEmpty()) {
            throw new NotFoundException(Department.class);
        }

        return departmentMapper.toDepartmentDto(
                department.get()
        );
    }

    private DepartmentDto findByName(String name) {
        Optional<Department> department = departmentService.findByName(name);
        if (department.isEmpty()) {
            throw new NotFoundException(Department.class);
        }

        return departmentMapper.toDepartmentDto(
                department.get()
        );
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentService.findAll()
                .stream()
                .map(departmentMapper::toDepartmentDto)
                .toList();
    }

    @Override
    public DepartmentDto save(@Valid DepartmentDto departmentDto,
                              BindingResult bindingResult) {
        Department department = departmentMapper.toDepartment(departmentDto);

        departmentValidator.validate(department, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return departmentMapper.toDepartmentDto(
                departmentService.save(department)
        );
    }

    @Override
    public DepartmentDto update(Long id,
                                @Valid DepartmentDto departmentDto,
                                BindingResult bindingResult) {
        Optional<Department> oldDepartment = departmentService.findById(id);
        if (oldDepartment.isEmpty()) {
            throw new NotFoundException(Department.class);
        }

        Department newDepartment = departmentMapper.toDepartment(departmentDto);
        departmentValidator.validate(id, newDepartment, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return departmentMapper.toDepartmentDto(
                departmentService.update(oldDepartment.get(), newDepartment)
        );
    }

    @Override
    public String delete(Long id) {

        if (departmentService.findById(id).isEmpty()) {
            throw new NotFoundException(Department.class);
        }

        departmentService.delete(id);
        return "Deleted";
    }
}
