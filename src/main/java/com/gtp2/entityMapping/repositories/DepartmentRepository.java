package com.gtp2.entityMapping.repositories;

import com.gtp2.entityMapping.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findByName(String name);

    // Find departments directed by a specific doctor
    @Query("SELECT dept FROM Department dept WHERE dept.director.id = :doctorId")
    List<Department> findDepartmentsByDirector(@Param("doctorId") String doctorId);
}
