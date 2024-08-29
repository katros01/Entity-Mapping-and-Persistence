package com.gtp2.entityMapping.services;

import com.gtp2.entityMapping.entities.Patient;
import com.gtp2.entityMapping.repositories.PatientRepository;
import com.gtp2.entityMapping.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    private static final String PATIENT_CACHE = "patients";

    @Cacheable(value = PATIENT_CACHE, key = "#id")
    public Optional<Patient> getPatientById(String id) {
        System.out.println("get patient from db");
        return patientRepository.findById(id);
    }

    @Cacheable(value = PATIENT_CACHE)
    public List<Patient> getAllPatients() {
        System.out.println("get patients from db");
        return patientRepository.findAll();
    }

    @Transactional
    @CachePut(value = PATIENT_CACHE, key = "#result.id")
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    @CachePut(value = PATIENT_CACHE, key = "#id")
    public Patient updatePatient(String id, Patient patientDetails) {
        System.out.println("update patient from db");
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setPatientNumber(patientDetails.getPatientNumber());
                    patient.setSurname(patientDetails.getSurname());
                    patient.setFirstName(patientDetails.getFirstName());
                    patient.setAddress(patientDetails.getAddress());
                    patient.setTelephoneNumber(patientDetails.getTelephoneNumber());
                    return patientRepository.save(patient);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    @Transactional
    @CacheEvict(value = PATIENT_CACHE, key = "#id")
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}
