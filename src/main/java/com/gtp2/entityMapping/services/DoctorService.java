package com.gtp2.entityMapping.services;

import com.gtp2.entityMapping.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.gtp2.entityMapping.entities.Doctor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Cacheable(value = "doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "doctors", key = "#id")
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Transactional
    @CachePut(value = "doctors", key = "#id")
    public Optional<Doctor> updateDoctor(String id, Doctor doctorDetails) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setEmployeeNumber(doctorDetails.getEmployeeNumber());
                    doctor.setSurname(doctorDetails.getSurname());
                    doctor.setFirstName(doctorDetails.getFirstName());
                    doctor.setAddress(doctorDetails.getAddress());
                    doctor.setTelephoneNumber(doctorDetails.getTelephoneNumber());
                    doctor.setSpeciality(doctorDetails.getSpeciality());
                    return doctorRepository.save(doctor);
                });
    }

    @Transactional
    @CacheEvict(value = "doctors", key = "#id")
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "doctors", key = "#speciality")
    public List<Doctor> findBySpeciality(String speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }
}

