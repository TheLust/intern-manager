package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.enums.Stage;

import java.util.List;
import java.util.Optional;

public interface IJobService {
    Optional<Job> findById(Long id);
    Optional<Job> findByNameAndStage(String name, Stage stage);
    List<Job> findAll();
    Job save(Job job);
    Job update(Job oldJob, Job newJob);
    void delete(Long id);
}
