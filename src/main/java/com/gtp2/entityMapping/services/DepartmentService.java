package com.gtp2.entityMapping.services;

import com.gtp2.entityMapping.dto.DepartmentDto;
import com.gtp2.entityMapping.entities.Doctor;
import com.gtp2.entityMapping.repositories.DepartmentRepository;
import com.gtp2.entityMapping.repositories.DoctorRepository;
import com.gtp2.entityMapping.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.gtp2.entityMapping.entities.Department;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    @Cacheable(value = "departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "departments", key = "#id")
    public Optional<Department> getDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Department createDepartment(DepartmentDto departmentRequest) {
        Doctor director = doctorRepository.findById(departmentRequest.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Department department = new Department();
        department.setDepartmentCode(departmentRequest.getDepartmentCode());
        department.setName(departmentRequest.getName());
        department.setBuilding(departmentRequest.getBuilding());
        department.setDirector(director);

        return departmentRepository.save(department);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @CachePut(value = "departments", key = "#id")
    public Department updateDepartment(String id, DepartmentDto departmentRequest) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Doctor director = doctorRepository.findById(departmentRequest.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        department.setDepartmentCode(departmentRequest.getDepartmentCode());
        department.setName(departmentRequest.getName());
        department.setBuilding(departmentRequest.getBuilding());
        department.setDirector(director);

        return departmentRepository.save(department);
    }

    @Transactional
    @CacheEvict(value = "departments", key = "#id")
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}

