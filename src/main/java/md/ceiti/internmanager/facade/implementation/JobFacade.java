package md.ceiti.internmanager.facade.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.JobDto;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.enums.Stage;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.facade.interfaces.IJobFacade;
import md.ceiti.internmanager.mapper.JobMapper;
import md.ceiti.internmanager.service.implementation.JobService;
import md.ceiti.internmanager.util.ErrorUtils;
import md.ceiti.internmanager.util.ExceptionMessage;
import md.ceiti.internmanager.validator.JobValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobFacade implements IJobFacade {

    private final JobService jobService;

    private final JobMapper jobMapper;

    private final JobValidator jobValidator;

    @Override
    public JobDto find(Long id,
                       String name,
                       Stage stage) {
        if ((id != null ? 1 : 0) + ((name != null || stage != null) ? 1 : 0) != 1) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_ARGUMENTS);
        }

        if (id != null) {
            return findById(id);
        }

        return findByNameAndStage(name, stage);
    }

    private JobDto findById(Long id) {
        Optional<Job> job = jobService.findById(id);
        if (job.isEmpty()) {
            throw new NotFoundException(Job.class);
        }

        return jobMapper.toJobDto(
                job.get()
        );
    }

    private JobDto findByNameAndStage(String name, Stage stage) {
        Optional<Job> job = jobService.findByNameAndStage(name, stage);
        if (job.isEmpty()) {
            throw new NotFoundException(Job.class);
        }

        return jobMapper.toJobDto(
                job.get()
        );
    }

    @Override
    public List<JobDto> findAll() {
        return jobService.findAll()
                .stream()
                .map(jobMapper::toJobDto)
                .toList();
    }

    @Override
    public JobDto save(@Valid JobDto jobDto,
                       BindingResult bindingResult) {
        Job job = jobMapper.toJob(jobDto);

        jobValidator.validate(job, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return jobMapper.toJobDto(
                jobService.save(job)
        );
    }

    @Override
    public JobDto update(Long id,
                         @Valid JobDto jobDto,
                         BindingResult bindingResult) {
        Optional<Job> oldJob = jobService.findById(id);
        if (oldJob.isEmpty()) {
            throw new NotFoundException(Job.class);
        }

        Job newJob = jobMapper.toJob(jobDto);
        jobValidator.validate(id, newJob, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return jobMapper.toJobDto(
                jobService.update(oldJob.get(), newJob)
        );
    }

    @Override
    public String delete(Long id) {
        jobService.delete(id);

        return "Deleted";
    }
}
