package md.ceiti.internmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.JobDto;
import md.ceiti.internmanager.enums.Stage;
import md.ceiti.internmanager.facade.implementation.JobFacade;
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
@RequestMapping("${api.url}/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobFacade jobFacade;

    @GetMapping("/")
    public ResponseEntity<JobDto> find(@RequestParam(value = "id", required = false) Long id,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "stage", required = false) Stage stage) {
        return new ResponseEntity<>(
                jobFacade.find(id, name, stage),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> findAll() {
        return new ResponseEntity<>(
                jobFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<JobDto> save(@RequestBody @Valid JobDto jobDto,
                                       BindingResult bindingResult) {
        return new ResponseEntity<>(
                jobFacade.save(jobDto, bindingResult),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<JobDto> update(@RequestParam("id") Long id,
                                         @RequestBody @Valid JobDto jobDto,
                                         BindingResult bindingResult) {
        return new ResponseEntity<>(
                jobFacade.update(id, jobDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                jobFacade.delete(id),
                HttpStatus.OK
        );
    }
}
