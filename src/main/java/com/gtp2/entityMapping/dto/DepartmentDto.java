package com.gtp2.entityMapping.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentDto {
    private String departmentCode;
    private String name;
    private String building;
    private String directorId;
}
