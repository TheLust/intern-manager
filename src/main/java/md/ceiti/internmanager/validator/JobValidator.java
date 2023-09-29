package md.ceiti.internmanager.validator;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.service.implementation.JobService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
@Component
@RequiredArgsConstructor
public class JobValidator implements Validator {

    private final JobService jobService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Job.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Job job = (Job) target;

        if (jobService.findByNameAndStage(job.getName(), job.getStage()).isPresent()) {
            errors.rejectValue("name", AlreadyExistsException.JOB);
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Job job = (Job) target;
        Optional<Job> foundJob = jobService.findByNameAndStage(job.getName(), job.getStage());

        if (foundJob.isPresent()) {
            if (!Objects.equals(foundJob.get().getId(), id)) {
                errors.rejectValue("name", AlreadyExistsException.JOB);
            }
        }
    }
}
