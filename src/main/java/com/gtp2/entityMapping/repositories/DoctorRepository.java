package com.gtp2.entityMapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gtp2.entityMapping.entities.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    @Query("SELECT d FROM Doctor d WHERE d.employeeNumber = :employeeNumber")
    Doctor findByEmployeeNumber(@Param("employeeNumber") Long employeeNumber);

    @Query("SELECT d FROM Doctor d WHERE d.speciality = ?1")
    List<Doctor> findBySpeciality(String speciality);

}