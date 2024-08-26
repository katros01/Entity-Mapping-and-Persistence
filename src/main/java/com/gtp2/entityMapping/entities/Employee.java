package com.gtp2.entityMapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "employees")
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "employee_number")
    private Long employeeNumber;

    private String surname;

    @Column(name = "first_name")
    private String firstName;

    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;

}
