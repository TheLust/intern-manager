package md.ceiti.internmanager.dao;

import md.ceiti.internmanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectDao extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
