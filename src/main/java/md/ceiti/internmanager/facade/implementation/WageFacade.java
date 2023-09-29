package md.ceiti.internmanager.facade.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.WageDto;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.entity.Wage;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IWageFacade;
import md.ceiti.internmanager.mapper.WageMapper;
import md.ceiti.internmanager.service.implementation.JobService;
import md.ceiti.internmanager.service.implementation.ProjectService;
import md.ceiti.internmanager.service.implementation.WageService;
import md.ceiti.internmanager.util.ErrorUtils;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.WageValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WageFacade  implements IWageFacade {

    private final WageService wageService;

    private final ProjectService projectService;

    private final JobService jobService;

    private final WageMapper wageMapper;

    private final WageValidator wageValidator;

    @Override
    public WageDto find(Long id, Long projectId, Long jobId) {
        if ((id != null ? 1 : 0) + ((jobId != null || projectId != null) ? 1 : 0) != 1) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        return findByProjectAndJob(projectId, jobId);
    }

    private WageDto findById(Long id) {
        Optional<Wage> wage = wageService.findById(id);
        if (wage.isEmpty()) {
            throw new NotFoundException(Wage.class);
        }

        return wageMapper.toWageDto(
                wage.get()
        );
    }

    private WageDto findByProjectAndJob(Long projectId,
                                        Long jobId) {
        Optional<Project> project = projectService.findById(projectId);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        Optional<Job> job = jobService.findById(jobId);
        if (job.isEmpty()) {
            throw new NotFoundException(Job.class);
        }

        Optional<Wage> wage = wageService.findByProjectAndJob(project.get(), job.get());
        if (wage.isEmpty()) {
            throw new NotFoundException(Wage.class);
        }

        return wageMapper.toWageDto(
                wage.get()
        );
    }

    @Override
    public List<WageDto> findAll() {
        return wageService.findAll()
                .stream()
                .map(wageMapper::toWageDto)
                .toList();
    }

    @Override
    public WageDto save(Long projectId,
                        Long jobId,
                        @Valid WageDto wageDto,
                        BindingResult bindingResult) {
        Optional<Project> project = projectService.findById(projectId);
        if (project.isEmpty()) {
            throw new NotFoundException(Project.class);
        }

        Optional<Job> job = jobService.findById(jobId);
        if (job.isEmpty()) {
            throw new NotFoundException(Job.class);
        }

        Wage wage = wageMapper.toWage(wageDto);
        wage.setProject(project.get());
        wage.setJob(job.get());

        wageValidator.validate(wage, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return wageMapper.toWageDto(
                wageService.save(wage)
        );
    }

    @Override
    public WageDto update(Long id,
                          Long projectId,
                          Long jobId,
                          @Valid WageDto wageDto,
                          BindingResult bindingResult) {
        Optional<Wage> oldWage = wageService.findById(id);
        if (oldWage.isEmpty()) {
            throw new NotFoundException(Wage.class);
        }

        Wage newWage = wageMapper.toWage(wageDto);

        if (projectId != null) {
            Optional<Project> project = projectService.findById(projectId);
            if (project.isEmpty()) {
                throw new NotFoundException(Project.class);
            }
            newWage.setProject(project.get());
        } else {
            newWage.setProject(oldWage.get().getProject());
        }

        if (jobId != null) {
            Optional<Job> job = jobService.findById(jobId);
            if (job.isEmpty()) {
                throw new NotFoundException(Job.class);
            }
            newWage.setJob(job.get());
        } else {
            newWage.setJob(oldWage.get().getJob());
        }

        wageValidator.validate(id, newWage, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return wageMapper.toWageDto(
                wageService.update(oldWage.get(), newWage)
        );
    }

    @Override
    public String delete(Long id) {
        wageService.delete(id);

        return "Deleted";
    }
}
