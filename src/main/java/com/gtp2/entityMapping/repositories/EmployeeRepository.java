package com.gtp2.entityMapping.repositories;

import com.gtp2.entityMapping.entities.Doctor;
import com.gtp2.entityMapping.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT d FROM Doctor d WHERE d.employeeNumber = :employeeNumber")
    Doctor findByEmployeeNumber(@Param("employeeNumber") Long employeeNumber);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %?1% OR e.surname LIKE %?1%")
    List<Employee> findByName(String name);

    List<Doctor> findBySurname(String surname);
}