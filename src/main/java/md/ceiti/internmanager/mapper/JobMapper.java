package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.JobDto;
import md.ceiti.internmanager.entity.Job;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobMapper {

    private final ModelMapper mapper;

    public Job toJob(JobDto jobDto) {
        return mapper.map(jobDto, Job.class);
    }

    public JobDto toJobDto(Job job) {
        return mapper.map(job, JobDto.class);
    }
}
