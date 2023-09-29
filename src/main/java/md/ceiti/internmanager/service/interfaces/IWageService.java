package md.ceiti.internmanager.service.interfaces;

import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.entity.Wage;

import java.util.List;
import java.util.Optional;

public interface IWageService {
    Optional<Wage> findById(Long id);
    Optional<Wage> findByProjectAndJob(Project project, Job job);
    List<Wage> findAll();
    Wage save(Wage wage);
    Wage update(Wage oldWage, Wage newWage);
    void delete(Long id);
}
