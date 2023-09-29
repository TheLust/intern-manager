package md.ceiti.internmanager.dao;

import jakarta.validation.constraints.NotNull;
import md.ceiti.internmanager.entity.Assignment;
import md.ceiti.internmanager.entity.Employee;
import md.ceiti.internmanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentDao extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByEmployeeAndProject(@NotNull Employee employee, @NotNull Project project);
}
