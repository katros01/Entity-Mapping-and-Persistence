package com.gtp2.entityMapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import  java.util.List;

@Setter
@Getter
@Entity
@Table(name = "doctors")
public class Doctor extends Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String speciality;

    @OneToMany(mappedBy = "director")
    private List<Department> departmentsDirected;

}
