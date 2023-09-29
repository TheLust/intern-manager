package md.ceiti.internmanager.dao;

import md.ceiti.internmanager.entity.Job;
import md.ceiti.internmanager.enums.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobDao extends JpaRepository<Job, Long> {
    Optional<Job> findByNameAndStage(String name, Stage stage);
}
