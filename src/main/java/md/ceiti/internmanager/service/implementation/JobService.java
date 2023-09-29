package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.JobDao;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.enums.Stage;
import md.ceiti.internmanager.service.interfaces.IJobService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService implements IJobService {

    private final JobDao jobDao;

    @Override
    public Optional<Job> findById(Long id) {
        return jobDao.findById(id);
    }

    @Override
    public Optional<Job> findByNameAndStage(String name, Stage stage) {
        return jobDao.findByNameAndStage(name, stage);
    }

    @Override
    public List<Job> findAll() {
        return jobDao.findAll();
    }

    @Override
    public Job save(Job job) {
        return jobDao.save(job);
    }

    @Override
    public Job update(Job oldJob, Job newJob) {
        BeanUtils.copyProperties(newJob, oldJob, "id");
        return jobDao.save(oldJob);
    }

    @Override
    public void delete(Long id) {
        jobDao.deleteById(id);
    }
}
