package com.gtp2.entityMapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import  java.util.List;

@Setter
@Getter
@Entity
@Table(name = "nurses")
public class Nurse extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String rotation;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_code", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "supervisor")
    private List<Ward> wardsSupervised;


}
