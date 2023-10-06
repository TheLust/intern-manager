package md.ceiti.internmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.ProjectDto;
import md.ceiti.internmanager.facade.implementation.ProjectFacade;
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
@RequestMapping("${api.url}/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @GetMapping("/")
    public ResponseEntity<ProjectDto> find(@RequestParam(value = "id", required = false) Long id,
                                           @RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(
                projectFacade.find(id, name),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        return new ResponseEntity<>(
                projectFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ProjectDto> save(@RequestBody @Valid ProjectDto projectDto,
                                           BindingResult bindingResult) {
        return new ResponseEntity<>(
                projectFacade.save(projectDto, bindingResult),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<ProjectDto> update(@RequestParam("id") Long id,
                                             @RequestBody @Valid ProjectDto projectDto,
                                             BindingResult bindingResult) {
        return new ResponseEntity<>(
                projectFacade.update(id, projectDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                projectFacade.delete(id),
                HttpStatus.OK
        );
    }
}
