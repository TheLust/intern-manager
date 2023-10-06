package md.ceiti.internmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.DepartmentDto;
import md.ceiti.internmanager.facade.implementation.DepartmentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.url}/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @GetMapping("/")
    public ResponseEntity<DepartmentDto> findById(@RequestParam(value = "id", required = false) Long id,
                                                  @RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(
                departmentFacade.find(id, name),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return new ResponseEntity<>(
                departmentFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> save(@RequestBody @Valid DepartmentDto departmentDto,
                                              BindingResult bindingResult) {
        return new ResponseEntity<>(
                departmentFacade.save(departmentDto, bindingResult),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<DepartmentDto> update(@RequestParam("id") Long id,
                                                @RequestBody @Valid DepartmentDto departmentDto,
                                                BindingResult bindingResult) {
        return new ResponseEntity<>(
                departmentFacade.update(id, departmentDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                departmentFacade.delete(id),
                HttpStatus.OK
        );
    }
}
