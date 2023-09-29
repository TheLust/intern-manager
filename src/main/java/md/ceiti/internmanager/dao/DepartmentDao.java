package md.ceiti.internmanager.dao;

import md.ceiti.internmanager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
