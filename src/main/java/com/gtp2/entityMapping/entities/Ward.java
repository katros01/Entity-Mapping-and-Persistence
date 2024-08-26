package com.gtp2.entityMapping.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "wards")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "number_of_beds")
    private Integer numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    private Nurse supervisor;

    @ManyToOne
    @JoinColumn(name = "department_code", insertable = false, updatable = false)
    private Department department;

    @Column(name = "ward_number")
    private int wardNumber;

}