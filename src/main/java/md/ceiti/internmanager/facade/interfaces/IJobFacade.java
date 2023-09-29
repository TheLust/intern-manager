package md.ceiti.internmanager.facade.interfaces;

import md.ceiti.internmanager.dto.JobDto;
import md.ceiti.internmanager.enums.Stage;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IJobFacade {
    JobDto find(Long id,
                String name,
                Stage stage);
    List<JobDto> findAll();
    JobDto save(JobDto jobDto,
                BindingResult bindingResult);
    JobDto update(Long id,
                  JobDto jobDto,
                  BindingResult bindingResult);
    String delete(Long id);
}
