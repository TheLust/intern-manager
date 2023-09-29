package md.ceiti.internmanager.facade.interfaces;

import md.ceiti.internmanager.dto.WageDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IWageFacade {
    WageDto find(Long id,
                 Long projectId,
                 Long jobId);
    List<WageDto> findAll();
    WageDto save(Long projectId,
                 Long jobId,
                 WageDto wageDto,
                 BindingResult bindingResult);
    WageDto update(Long id,
                   Long projectId,
                   Long jobId,
                   WageDto wageDto,
                   BindingResult bindingResult);
    String delete(Long id);
}
