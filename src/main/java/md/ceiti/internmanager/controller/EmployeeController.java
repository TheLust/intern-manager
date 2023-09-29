package md.ceiti.internmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.EmployeeDto;
import md.ceiti.internmanager.facade.implementation.EmployeeFacade;
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
@RequestMapping("${api.url.base}/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @GetMapping("/")
    public ResponseEntity<EmployeeDto> find(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam(value = "email", required = false) String email,
                                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        return new ResponseEntity<>(
                employeeFacade.find(id, email, phoneNumber),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return new ResponseEntity<>(
                employeeFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> save(@RequestParam("department") Long departmentId,
                                            @RequestParam("job") Long jobId,
                                            @RequestBody @Valid EmployeeDto employeeDto,
                                            BindingResult bindingResult) {
        return new ResponseEntity<>(
                employeeFacade.save(departmentId, jobId, employeeDto, bindingResult),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<EmployeeDto> update(@RequestParam("id") Long id,
                                              @RequestParam(value = "department", required = false) Long departmentId,
                                              @RequestParam(value = "job", required = false) Long jobId,
                                              @RequestBody @Valid EmployeeDto employeeDto,
                                              BindingResult bindingResult) {
        return new ResponseEntity<>(
                employeeFacade.update(id, departmentId, jobId, employeeDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                employeeFacade.delete(id),
                HttpStatus.OK
        );
    }
}
