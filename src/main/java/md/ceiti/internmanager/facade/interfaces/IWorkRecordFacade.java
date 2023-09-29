package md.ceiti.internmanager.facade.interfaces;

import md.ceiti.internmanager.dto.WorkRecordDto;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

public interface IWorkRecordFacade {
    WorkRecordDto find(Long id, Long assignmentId, LocalDate date);
    List<WorkRecordDto> getAll();
    WorkRecordDto save(Long assignmentId,
                       WorkRecordDto workRecordDto,
                       BindingResult bindingResult);
    WorkRecordDto update(Long id,
                         Long assignmentId,
                         WorkRecordDto workRecordDto,
                         BindingResult bindingResult);
    String delete(Long id);
}
