package md.ceiti.internmanager.dao;

import jakarta.validation.constraints.NotNull;
import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.entity.Project;
import md.ceiti.internmanager.entity.Wage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WageDao extends JpaRepository<Wage, Long> {
    Optional<Wage> findByProjectAndJob(@NotNull Project project, @NotNull Job job);
}
