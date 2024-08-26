package com.gtp2.entityMapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "department_code")
    private String departmentCode;

    private String name;
    private String building;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Doctor director;

    @OneToMany(mappedBy = "department")
    private List<Nurse> nurses;

    @OneToMany(mappedBy = "department")
    private List<Ward> wards;


}
