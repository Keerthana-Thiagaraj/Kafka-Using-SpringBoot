package com.kafkaExample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeModel {

    @NotNull
    private Integer empId;

    @NotBlank
    private String empName;

    @NotBlank
    private String empGender;
}
