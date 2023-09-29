package md.ceiti.internmanager.facade.interfaces;

import md.ceiti.internmanager.dto.DepartmentDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IDepartmentFacade {

    DepartmentDto find(Long id, String name);
    List<DepartmentDto> findAll();
    DepartmentDto save(DepartmentDto departmentDto,
                       BindingResult bindingResult);
    DepartmentDto update(Long id,
                         DepartmentDto departmentDto,
                         BindingResult bindingResult);
    String delete(Long id);
}
