package md.ceiti.internmanager.controller;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.AssignmentDto;
import md.ceiti.internmanager.facade.implementation.AssignmentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.url}/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentFacade assignmentFacade;

    @GetMapping("/")
    public ResponseEntity<AssignmentDto> find(@RequestParam(value = "id", required = false) Long id,
                                              @RequestParam(value = "employee", required = false) Long employeeId,
                                              @RequestParam(value = "project", required = false) Long projectId) {
        return new ResponseEntity<>(
                assignmentFacade.find(id, employeeId, projectId),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<AssignmentDto>> getAll() {
        return new ResponseEntity<>(
                assignmentFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<AssignmentDto> save(@RequestParam("employee") Long employeeId,
                                              @RequestParam("project") Long projectId) {
        return new ResponseEntity<>(
                assignmentFacade.save(employeeId, projectId),
                HttpStatus.OK
        );
    }

    @PutMapping("/")
    public ResponseEntity<AssignmentDto> update(@RequestParam("id") Long id,
                                                @RequestParam("employee") Long employeeId,
                                                @RequestParam("project") Long projectId) {
        return new ResponseEntity<>(
                assignmentFacade.update(id, employeeId, projectId),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                assignmentFacade.delete(id),
                HttpStatus.OK
        );
    }
}
