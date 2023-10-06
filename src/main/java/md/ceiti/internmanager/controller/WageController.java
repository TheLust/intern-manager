package md.ceiti.internmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.WageDto;
import md.ceiti.internmanager.facade.implementation.WageFacade;
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
@RequestMapping("${api.url}/wages")
@RequiredArgsConstructor
public class WageController {

    private final WageFacade wageFacade;

    @GetMapping("/")
    public ResponseEntity<WageDto> find(@RequestParam(value = "id", required = false) Long id,
                                        @RequestParam(value = "project", required = false) Long projectId,
                                        @RequestParam(value = "job", required = false) Long jobId) {
        return new ResponseEntity<>(
                wageFacade.find(id, projectId, jobId),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<WageDto>> findAll() {
        return new ResponseEntity<>(
                wageFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<WageDto> save(@RequestParam("project") Long projectId,
                                        @RequestParam("job") Long jobId,
                                        @RequestBody @Valid WageDto wageDto,
                                        BindingResult bindingResult) {
        return new ResponseEntity<>(
                wageFacade.save(projectId, jobId, wageDto, bindingResult),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<WageDto> update(@RequestParam("id") Long id,
                                          @RequestParam(value = "project", required = false) Long projectId,
                                          @RequestParam(value = "job", required = false) Long jobId,
                                          @RequestBody @Valid WageDto wageDto,
                                          BindingResult bindingResult) {
        return new ResponseEntity<>(
                wageFacade.update(id, projectId, jobId, wageDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                wageFacade.delete(id),
                HttpStatus.OK
        );
    }
}
