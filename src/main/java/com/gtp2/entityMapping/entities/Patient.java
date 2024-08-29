package com.gtp2.entityMapping.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Document(collection = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long patientNumber;

    private String surname;

    private String firstName;

    private String address;

    private String telephoneNumber;

}
