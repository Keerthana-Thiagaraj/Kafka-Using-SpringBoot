package com.kafkaExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeModel {

    private Integer empId;

    private String empName;

    private String empGender;
}
