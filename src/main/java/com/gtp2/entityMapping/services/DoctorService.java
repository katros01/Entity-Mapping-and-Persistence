package com.gtp2.entityMapping.services;

import com.gtp2.entityMapping.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gtp2.entityMapping.entities.Doctor;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

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

    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> findBySpeciality(String speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }
}

