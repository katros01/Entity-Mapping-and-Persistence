package com.gtp2.entityMapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "patient_number")
    private Long patientNumber;

    private String surname;

    @Column(name = "first_name")
    private String firstName;

    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;



}
