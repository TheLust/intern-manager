package md.ceiti.internmanager.service.implementation;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dao.WageDao;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.entity.Wage;
import md.ceiti.internmanager.service.interfaces.IWageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WageService implements IWageService {

    private final WageDao wageDao;

    @Override
    public Optional<Wage> findById(Long id) {
        return wageDao.findById(id);
    }

    @Override
    public Optional<Wage> findByProjectAndJob(Project project, Job job) {
        return wageDao.findByProjectAndJob(project, job);
    }

    @Override
    public List<Wage> findAll() {
        return wageDao.findAll();
    }

    @Override
    public Wage save(Wage wage) {
        return wageDao.save(wage);
    }

    @Override
    public Wage update(Wage oldWage, Wage newWage) {
        BeanUtils.copyProperties(newWage, oldWage, "id");
        return wageDao.save(oldWage);
    }

    @Override
    public void delete(Long id) {
        wageDao.deleteById(id);
    }
}
