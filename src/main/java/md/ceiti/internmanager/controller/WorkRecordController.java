package md.ceiti.internmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.WorkRecordDto;
import md.ceiti.internmanager.facade.implementation.WorkRecordFacade;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${api.url.base}/records")
@RequiredArgsConstructor
public class WorkRecordController {

    private final WorkRecordFacade workRecordFacade;

    @GetMapping("/")
    public ResponseEntity<WorkRecordDto> find(@RequestParam(value = "id", required = false) Long id,
                                              @RequestParam(value = "assignment", required = false) Long assignmentId,
                                              @RequestParam(value = "date", required = false) LocalDate date) {
        return new ResponseEntity<>(
                workRecordFacade.find(id, assignmentId, date),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<WorkRecordDto>> findAll() {
        return new ResponseEntity<>(
                workRecordFacade.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<WorkRecordDto> save(@RequestParam("assignment") Long assignmentId,
                                              @RequestBody @Valid WorkRecordDto workRecordDto,
                                              BindingResult bindingResult) {
        return new ResponseEntity<>(
                workRecordFacade.save(assignmentId, workRecordDto, bindingResult),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<WorkRecordDto> update(@RequestParam("id") Long id,
                                                @RequestParam(value = "assignment", required = false) Long assignmentId,
                                                @RequestBody @Valid WorkRecordDto workRecordDto,
                                                BindingResult bindingResult) {
        return new ResponseEntity<>(
                workRecordFacade.update(id, assignmentId, workRecordDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                workRecordFacade.delete(id),
                HttpStatus.OK
        );
    }
}
