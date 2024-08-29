package com.gtp2.entityMapping.repositories;

import com.gtp2.entityMapping.entities.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Patient findByPatientNumber(Long patientNumber);
}